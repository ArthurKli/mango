package com.g3net.database;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.g3net.database.dbpool.DBPoolFactory;
import com.g3net.database.sql.BeanUtils;
import com.g3net.database.sql.SqlUtils;
import com.g3net.tool.ArrayUtils;
import com.g3net.tool.Assert;
import com.g3net.tool.CollectionUtils;
import com.g3net.tool.ObjectUtils;
import com.g3net.tool.PageBean;
import com.g3net.tool.StringUtils;
import com.g3net.type.TInteger;
import com.g3net.type.TString;
import com.sun.rowset.CachedRowSetImpl;

/**
 * <code>DataBase</code>类用于封装了执行数据库的一些操作，功能强大，具有从sql语句到对象的映射功能，<br/>
 * 如果使用对象映射功能的方法，所有的映射对象的类可以通过 {@link com.g3net.database.sql.SqlUtils}类的
 * exportTables方法生成 <br/>
 * <p>
 * DataBase对象不是线程安全的，这就意味着多个线程通常使用一个DataBase对象会出现不安全隐患。记住，你要自己处理线程
 * 安全问题，你可以通过ThreadLocal或线程同步来解决多个线程访问一个DataBase对象
 * </p>
 * <br/>
 * 
 * 用法1：
 * <p>
 * <blockquote>
 * 
 * <pre>
 * String sql = &quot;select * from photos p where p.newsid = ?&quot;
 * 		+ &quot; and (p.audi_stat = ? or p.audi_stat = ?)&quot;;
 * 
 * List list = new ArrayList();
 * 
 * try {
 * 	Map&lt;Integer, Object&gt; args = new HashMap&lt;Integer, Object&gt;();
 * 	args.put(1, newsid);
 * 	args.put(2, 1);
 * 	args.put(3, 4);
 * 	list = db.doQueryClass(Photos.class, sql, args);
 * } catch (DbException e) {
 * 	// TODO Auto-generated catch block
 * 	log.error(&quot;&quot;, e);
 * }
 *</pre>
 * 
 * </blockquote>
 * </p>
 * 用法2：
 * <p>
 * <blockquote>
 * 
 * <pre>
 * DataBase db = DataBaseFactory.getDataBase();
 * String sql = "select * from news n where n.type =? and id=? and audi=1 order by n.id desc ";
 * 		
 * Map<Integer, Object> args = new HashMap<Integer, Object>();
 * 
 * args.put(1, Integer.valueOf(100));
 * args.put(2, 234);
 * int page=2//当前第几页
 * int perpage=20//每页20条
 * PageBean pageUtils = new PageBean();//用于存放分页信息，包括总页数，当前多少页，总共多少行等 
 * list = db.doPageQueryClass(News.class, sql, args, page, perpage, pageUtils, null);
 * int maxPage=pageUtils.getMaxPage();//最大页
 * int curPage =pageUtils.getPage();//当前页
 * int prePage =pageUtils.getPrevPage();//前一页
 *</pre>
 * 
 * </blockquote>
 * </p>
 * 用法3:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * DataBase db = DataBaseFactory.getDataBase();
 * String sqlx = &quot;select * from news where id=? and name=?&quot;;
 * Map&lt;Integer, Object&gt; vparms = new HashMap&lt;Integer, Object&gt;();
 * vparms.put(1, 23);
 * vparms.put(2, &quot;娱乐&quot;);
 * PageBean pageUtils = new PageBean();//用于存放分页信息，包括总页数，当前多少页，总共多少行等
 * int page = 2;//当前页
 * int perPage = 20;//每页多少行
 * DataBaseSet rs = db.doCachedPageQuery(sqlx, vparms, page, perPage, pageUtils,
 * 		null);
 * 
 * while (rs.next()) {
 * 	String name = rs.getString(&quot;name&quot;);
 * }
 * int maxPage = pageUtils.getMaxPage();//最大页
 * int curPage = pageUtils.getPage();//当前页
 * int prePage = pageUtils.getPrevPage();//前一页
 * </pre>
 * 
 * </blockquote>
 * </p>
 * 用法4:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * String sql = &quot;update product_version set action=? where id=?&quot;;
 * DataBase db = null;
 * 
 * db = DataBaseFactory.getDataBase();
 * List&lt;Map&lt;Integer, Object&gt;&gt; list = new ArrayList&lt;Map&lt;Integer, Object&gt;&gt;();
 * for (int i = 0; i &lt; ids.length; i++) {
 * 	Map&lt;Integer, Object&gt; map = new HashMap&lt;Integer, Object&gt;();
 * 	map.put(1, action);
 * 	map.put(2, ids[i]);
 * 	list.add(map);
 * }
 * int[] results = db.executeBindBatch(sql, list);
 * 
 *</pre>
 * 
 * </blockquote>
 * </p>
 *用法5:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * public static int deleteBy(int[] ids) {
 * 
 * 	StringBuilder sql = new StringBuilder(
 * 			&quot;delete from platform where id=? and id not in (select platformid &quot;
 * 					+ &quot; from productinfo)&quot;);
 * 	DataBase db = null;
 * 
 * 	try {
 * 		List&lt;Map&lt;Integer, Object&gt;&gt; vParametersList = new ArrayList&lt;Map&lt;Integer, Object&gt;&gt;();
 * 
 * 		for (int i = 0; i &lt; ids.length; i++) {
 * 			Map&lt;Integer, Object&gt; args = new HashMap&lt;Integer, Object&gt;();
 * 			args.put(1, ids[i]);
 * 			vParametersList.add(args);
 * 		}
 * 		db = DataBaseFactory.getDataBase();
 * 		int[] is = db.executeBindBatch(sql.toString(), vParametersList);
 * 		if (is == null) {
 * 			return 0;
 * 		} else {
 * 			for (int i = 0; i &lt; is.length; i++) {
 * 				if (is[i] == 0) {
 * 					return 0;
 * 				}
 * 			}
 * 		}
 * 		return 1;
 * 
 * 	} catch (DbException e) {
 * 		// TODO Auto-generated catch block
 * 		// e.printStackTrace();
 * 		log.error(&quot;&quot;, e);
 * 	}
 * 	return 0;
 * }
 *</pre>
 * 
 * </blockquote>
 * </p>
 *用法6: <br/>
 *如果涉及到事务性操作，可以设置db.setAutoCommit(false)见下面的例子，用户可以显式控制事务性操作，事务性操作完成后一定要在最后通过db
 * .close()关闭数据库连接，<br/>
 *否则会用完连接；如果不是事务性操作，不需要通过db.close()，因为每次调用后都
 * <p>
 * <blockquote>
 * 
 * <pre>
 * public static int add(Version p) {
 * 
 * 	String sql = &quot;insert into versions(version) values(?)&quot;;
 * 	Map&lt;Integer, Object&gt; args = new HashMap&lt;Integer, Object&gt;();
 * 	args.put(1, p.getVersion());
 * 	DataBase db = null;
 * 	try {
 * 		db = DataBaseFactory.getDataBase();
 * 		db.setAutoCommit(false);
 * 		int i = db.executeBindInsert(sql, args);
 * 		String sql1 = &quot;update versions set bakid=&quot; + i + &quot; where id=&quot; + i;
 * 		i = db.executeBindUpdate(sql1, null);
 * 		db.commit();
 * 		return i;
 * 	} catch (DbException e) {
 * 		// TODO Auto-generated catch block
 * 		e.printStackTrace();
 * 		try {
 * 			db.rollback();
 * 		} catch (DbException e1) {
 * 		}
 * 		log.error(&quot;&quot;, e);
 * 	} finally {
 * 		db.close();
 * 	}
 * 	return 0;
 * }
 *</pre>
 * 
 * </blockquote>
 * </p>
 * 用法7:<br/>
 * <p>
 * <blockquote>
 * 
 * <pre>
 * try {
 * 
 * 	String sql = &quot;select n.*,p.*,a.* from news n left outer join photos p&quot;
 * 			+ &quot; on   n.id=p.newsid  left outer join archives a &quot;
 * 			+ &quot; on n.id=a.newsid  where n.type=?  and n.audi=1 order by n.id desc&quot;;
 * 
 * 	Map&lt;Integer, Object&gt; vParameters = new HashMap&lt;Integer, Object&gt;();
 * 	vParameters.put(1, 1232);
 * 	DataBase db = DataBaseFactory.getDataBase();
 * 	QueryMapNestOne2Many query1 = new QueryMapNestOne2Many();
 * 	query1.set(Photos.class, &quot;photosList&quot;, &quot;id&quot;, &quot;p.&quot;, null);
 * 	QueryMapNestOne2Many query2 = new QueryMapNestOne2Many();
 * 	query2.set(Archives.class, &quot;archivesList&quot;, &quot;id&quot;, &quot;a.&quot;, null);
 * 
 * 	list = db.doQueryClassOne2Many(News.class, &quot;n.&quot;, &quot;id&quot;, sql, vParameters,
 * 			new QueryMapNestOne2Many[] { query1, query2 });
 * 
 * } catch (Exception ex) {
 * 	ex.printStackTrace();
 * }
 *</pre>
 * 
 * </blockquote>
 * </p>
 */
public class DataBase {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private static Logger log = Logger.getLogger(DataBase.class);
	private CallableStatement cs = null;

	private boolean needAutoReconnect = false;

	public boolean isAutoReconnect() {
		return needAutoReconnect;
	}

	private boolean fromDistributeTransaction = false;
	private boolean isAutoCommit = true;

	public void setAutoReconnect(boolean autoReconnect) {
		this.needAutoReconnect = autoReconnect;
	}

	static enum SQLType {
		INSERT, UPDATE, SELECT, STORE_DPROCEDURE
	}

	private SQLType sqlType = SQLType.SELECT;

	//private boolean isPageQuery = false;// 是否为分页查询
	//private int rsStart = 0;
	//private int rsEnd = 0;

	private String dbUser = null;;

	private String dbPass = null;

	private String dbUrl = null;;

	private DataSource dataSource = null;

	static class DataBaseType {
		public static String MYSQL = "mysql";
		public static String MS_SQL_SERVER = "microsoft sql server";
		public static String ORACLE = "oracle";
		public static String OTHER = "other";
	}

	private String dataBaseType = DataBaseType.OTHER;
	private int dataBaseMajorVersion = 0;
	private int dataBaseMinorVersion = 0;
	private int dataBaseDriverVersion = 0;
	private String driverClass = null;;

	private String dbPoolName = null;
	private String jndiPoolName = null;

	/*
	 * CONNECT: 表示直连 JNDI：表示通过JNDI获得连接 POOL：从连接池里获得连接 EXTERNAL:从外部传进的connection
	 */
	static enum ConnectType {
		CONNECT, JNDI, POOL, EXTERNAL, DATASOURCE
	};

	// private
	private ConnectType connectType = null;

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	private void setInteralConnectionAutoCommit(boolean autoCommit)
			throws DbException {
		try {
			if (this.fromDistributeTransaction) {
				return;
			}
			this.conn.setAutoCommit(autoCommit);
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public boolean getInternalConnectionAutoCommit() throws DbException {
		try {

			return this.conn.getAutoCommit();
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public DataBase() {

	}

	private void setDataBaseType(Connection conn) throws DbException {

		try {
			DatabaseMetaData dm = conn.getMetaData();
			String url = dm.getURL().trim().toLowerCase();

			String databaseProductName = "";

			databaseProductName = dm.getDatabaseProductName().toLowerCase();

			try {
				this.dataBaseMajorVersion = dm.getDatabaseMajorVersion();
				this.dataBaseMinorVersion = dm.getDatabaseMinorVersion();
				this.dataBaseDriverVersion = dm.getDriverMajorVersion();
			} catch (Throwable e) {
				this.dataBaseDriverVersion = 0;
				this.dataBaseMinorVersion = 0;
				this.dataBaseMajorVersion = 0;
				log.error("++++++");
			}
			if (databaseProductName.contains(DataBaseType.MYSQL)) {

				this.dataBaseType = DataBaseType.MYSQL;
			} else if (databaseProductName.contains(DataBaseType.MS_SQL_SERVER)) {

				this.dataBaseType = DataBaseType.MS_SQL_SERVER;

			} else if (databaseProductName.contains(DataBaseType.ORACLE)) {

				this.dataBaseType = DataBaseType.ORACLE;
			} else {

				this.dataBaseType = DataBaseType.OTHER;

			}

			log.debug("dataBaseType=" + this.dataBaseType);
		} catch (Exception e) {
			throw new DbException("get pool connection error!", e);
		}
	}

	/**
	 * 从数据源获得连接
	 * 
	 * @param ds
	 *            数据源
	 * @param fromDistributeTransaction
	 *            是否是分布式事务的数据源
	 * @throws DbException
	 */
	public void connectDb(DataSource ds, boolean fromDistributeTransaction)
			throws DbException {

		try {

			this.fromDistributeTransaction = true;
			this.connectType = ConnectType.DATASOURCE;
			this.conn = ds.getConnection();
			this.dataSource = ds;
			this.setDataBaseType(conn);
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	/**
	 * 从已有的连接获得连接
	 * 
	 * @param connection
	 */
	public void connectDb(Connection connection) throws DbException {
		this.connectType = ConnectType.EXTERNAL;// 从外部传入的连接
		this.conn = connection;
		this.setDataBaseType(conn);
	}

	/**
	 * 从dbpool.xml里设置的连接池获得连接
	 * 
	 * @param dbPoolName
	 *            对应于dbpool.xml里的元素dbpool的name属性值
	 * @throws DbException
	 */
	public void connectDb(String dbPoolName) throws DbException {

		this.connectType = ConnectType.POOL;
		this.dbPoolName = dbPoolName;
		DBPoolFactory dbf = DBPoolFactory.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		DataSource datasource = dbf.getDBPool(dbPoolName, map);
		// this.dbUrl = map.get("url");
		// this.dbUser = map.get("username");
		// this.dbPass = map.get("password");
		// this.driverClass = map.get("driverClassName");
		// 

		try {
			if (conn != null)
				return;
			conn = datasource.getConnection();
			// log.info("----------"+conn.getTransactionIsolation());
			this.setDataBaseType(conn);

		} catch (Exception e) {
			throw new DbException("get pool connection error!", e);
		}

	}

	/**
	 * 通过属性获得连接
	 * 
	 * @param dbUser
	 *            用户名
	 * @param dbPass
	 *            用户密码
	 * @param dbUrl
	 *            数据库连接url
	 * @param driverClass
	 *            驱动程序名
	 * @throws DbException
	 */
	public void connectDb(String dbUser, String dbPass, String dbUrl,
			String driverClass) throws DbException {
		this.connectType = ConnectType.CONNECT;
		this.setDbUser(dbUser);
		this.setDbPass(dbPass);
		this.setDbUrl(dbUrl);
		this.setDriverClass(driverClass);
		this.connectDb();
	}

	/**
	 * 通过J2EE服务器（如tomcat）的jNDI获得连接
	 * 
	 * @param jndiPoolName
	 * @throws DbException
	 */
	public void connectJNDI(String jndiPoolName) throws DbException {
		try {
			this.connectType = ConnectType.JNDI;
			this.jndiPoolName = jndiPoolName;
			InitialContext initCtx = new InitialContext();
			// in/itCtx.
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			DataSource datasource = (DataSource) envCtx.lookup(dbPoolName);

			conn = datasource.getConnection();
			this.setDataBaseType(conn);

		} catch (Exception e) {
			log.error("", e);
			throw new DbException("error in connectJNDI()!\r\n" + e);
		}
	}

	private void connectDb() throws DbException {

		try {

			this.connectType = ConnectType.CONNECT;
			// Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			// conn=DriverManager.getConnection(
			// "jdbc:microsoft:sqlserver://192.168.26.144:1433;DatabaseName=crct;user=sa;password=crctzt"
			// );
			/*
			 * Class.forName("com.mysql.jdbc.Driver"); String sConnStr =
			 * "jdbc:mysql://192.168.3.143:3306/gggamedb"; conn =
			 * DriverManager.getConnection(sConnStr,"root","abc");
			 */
			if (this.conn != null)
				return;
			Class.forName(this.driverClass);
			// String sConnStr = "jdbc:mysql://192.168.3.143:3306/gggamedb";
			log.debug(dbUrl + "," + dbUser + "," + dbPass);
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

			this.setDataBaseType(conn);
			// String sConnStr = "jdbc:mysql://219.136.248.237:3306/gggamedb";
			// conn = DriverManager.getConnection(sConnStr,"gameuser","jb98");
			/*
			 * String path=Logger.class.getResource("/").getPath(); String
			 * strurl="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
			 * + "/usr/tomcat5.5/database/db1.mdb";
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 * conn=DriverManager.getConnection(strurl) ;
			 */
			// InitialContext initCtx = new InitialContext();
			// Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// DataSource datasource = (DataSource)
			// envCtx.lookup("jdbc/gggamedb");
			//
			// conn = datasource.getConnection();
			// //log.info("----------" + datasource + "--------");
			// //log.info("----------  --------");
		} catch (Exception e) {
			// log.info("----------error=" + e);
			log.error("", e);
			throw new DbException("error in connectDb()!\r\n" + e);
		}
		if (conn == null) {
			// System.err.print("conn is null");
			log.error("conn is null");
			throw new DbException("error in connectDb() and conn is null!\r\n");
		}
	}

	/**
	 * 此方法返回查询到的离线结果集，操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭
	 * 
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return 返回查询到的结果集
	 * @throws DbException
	 */
	public DataBaseSet doCachedQuery(String sqlQuery,
			Map<Integer, Object> vParameters) throws DbException {
		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return new DataBaseSet(doCachedQuery(sqlQuery, args));
	}

	/**
	 * 此方法返回查询到的离线结果集，操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭 连接
	 * 
	 * @param sqlQuery
	 * @param vParameters
	 * @param page
	 *            当前页
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            返回的分页信息
	 * @param countSql
	 *            查询总数的sql语句，根据它查询总数；也可以指定一个整数字符串，用于指定总数；
	 *            如果指定null或""字符串，那么系统会根据sqlQuery自动生成查询总数sql语句
	 * @return
	 * @throws DbException
	 */
	public DataBaseSet doCachedPageQuery(String sqlQuery,
			Map<Integer, Object> vParameters, int page, int perPage,
			PageBean pageUtils, String countSql) throws DbException {

		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return new DataBaseSet(this.doCachedPageQuery(sqlQuery, args, page,
				perPage, pageUtils, this.getAutoCommit(), countSql));
	}

	/**
	 * 此方法操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭 连接
	 * 
	 * @param <T>
	 *            需映射的类
	 * @param sqlQuery
	 *            sql查询语句
	 * @param args
	 *            sql查询语句里的参数
	 * @param page
	 *            当前页，从第一页开始
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            返回分页信息
	 * @param rowMapper
	 *            映射接口，用户可以通过此接口的回调函数来执行映射
	 * @param countSql
	 *            查询总数的sql语句，根据它查询总数；也可以指定一个整数字符串，用于指定总数；
	 *            如果指定null或""字符串，那么系统会根据sqlQuery自动生成查询总数sql语句
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doPageQueryObject(String sqlQuery,
			Map<Integer, Object> args, int page, int perPage,
			PageBean pageUtils, RowMapper<T> rowMapper, String countSql)
			throws DbException {

		Collection<Object> vParameters = CollectionUtils.getSortedValues(args);

		TInteger rsStart=new TInteger();
		TInteger rsEnd=new TInteger();
		String pageSql = this.getPagedSql(sqlQuery, vParameters, page, perPage,
				pageUtils, countSql,rsStart,rsEnd);
		return this.doQueryObject(pageSql, vParameters, rowMapper,true,rsStart.getValue(),rsEnd.getValue());

	}

	private RowSet doCachedQuery(String sqlQuery, Collection<Object> vParameters)
			throws DbException {
		return doCachedQuery(sqlQuery, vParameters, this.getAutoCommit());
	}

	private CachedRowSet doCachedPageQuery(String sqlQuery,
			Collection<Object> vParameters, int page, int perPage,
			PageBean pageUtils, boolean close, String countSql)
			throws DbException {

		CachedRowSet crs = null;
		try {
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}

			TInteger rsStart=new TInteger();
			TInteger rsEnd=new TInteger();
			String pageSql = this.getPagedSql(sqlQuery, vParameters, page,
					perPage, pageUtils, countSql,rsStart,rsEnd);

			ResultSet rs = (this.doQuery(pageSql, vParameters)).getResultSet();
			this.rs = rs;

			crs = this.getCachedRowSet(rs,true,rsStart.getValue(),rsEnd.getValue());

		} catch (Exception e) {
			log.error("", e);
			crs = null;
			throw new DbException("");
		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		return crs;

	}

	private CachedRowSet doCachedQuery(String sqlQuery,
			Collection<Object> vParameters, boolean close) throws DbException {

		CachedRowSet crs = null;
		try {

			crs = new CachedRowSetImpl();
			ResultSet rs = (this.doQuery(sqlQuery, vParameters)).getResultSet();
			this.rs = rs;

			crs.populate(rs);

		} catch (Exception e) {
			log.error("", e);
			crs = null;
			throw new DbException("");
		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		return crs;

	}

	/**
	 * 调用此方法不会关闭底层连接和结果集,需要调用DataBase.close()方法关闭它们
	 * 
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return 返回在线结果集
	 */
	private DataBaseSet doQuery(String sqlQuery,
			Map<Integer, Object> vParameters) throws DbException {

		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return this.doQuery(sqlQuery, args);
	}

	/**
	 * 调用此方法会默认自动关闭底层数据库连接，返回查询到的T对象列表
	 * 
	 * @param clazz
	 *            需映射的类
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return
	 */
	public <T> List<T> doQueryClass(Class<T> clazz, String sqlQuery,
			Map<Integer, Object> vParameters) throws DbException {

		return this.doQueryClassOne2One(clazz, null, sqlQuery, vParameters,
				(QueryMapNestOne2One[]) null);

	}

	// RowMapper rowMapper



	/**
	 * 一到一关联映射查询，调用此方法会默认自动关闭底层数据库连接，
	 * 
	 * @param <T>
	 *            需映射的主类的Class对象
	 * @param clazz
	 *            需映射到类的Class对象
	 * @param sqlPrefix
	 *            sql前缀
	 *            <p>
	 *            <blockquote>
	 * 
	 *            <pre>
	 * String sql = &quot;select n.*,p.*,a.* from news n left outer join photos p&quot;
	 * 		+ &quot; on   n.id=p.newsid  left outer join archives a &quot;
	 * 		+ &quot; on n.id=a.newsid  where n.type=?  and n.audi=1 order by n.id desc&quot;;
	 *</pre>
	 * 
	 *            </blockquote>
	 *            </p>
	 * <br/>
	 *            如果主类对应于n表(news)，那么sql前缀为"n."
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @param queryMapNestList
	 *            一到一映射关系，可以有多个映射关系，一个映射关系对应一个QueryMapNestOne2One对象
	 * @return 返回查询到的列表
	 * @throws DbException
	 */
	public <T> List<T> doQueryClassOne2One(Class<T> clazz, String sqlPrefix,
			String sqlQuery, Map<Integer, Object> vParameters,
			QueryMapNestOne2One[] queryMapNestList) throws DbException {
		return this.doQueryClass(clazz, sqlQuery, vParameters,
				queryMapNestList, sqlPrefix, null, this.getAutoCommit(),false,0,0);

	}

	/**
	 * 此方法操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭 连接
	 * 
	 * @param clazz
	 *            需映射类的Class对象
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @param page
	 *            当前页，从第一页开始
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            返回分页信息的类
	 * @param countSql
	 *            总行数的查询语句，也可以为一个整数字符串，表明总行数是多少，
	 *            如果为null或""，系统为自动根据sqlQuery字符串生产总行数的查询语句
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doPageQueryClass(Class<T> clazz, String sqlQuery,
			Map<Integer, Object> vParameters, int page, int perPage,
			PageBean pageUtils, String countSql) throws DbException {
		return this.doPageQueryClass(clazz, null, sqlQuery, vParameters,
				(QueryMapNestOne2One[]) null, page, perPage, pageUtils,
				countSql);
	}

	/**
	 * 此方法操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭 连接
	 * 
	 * @param <T>
	 * @param clazz
	 *            需映射主类的Class对象
	 * @param sqlPrefix
	 *            主类对应的sql前缀
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @param queryMapNestList
	 *            一到一映射关系
	 * @param page
	 *            当前页，从第一页开始
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            返回分页信息的类
	 * @param countSql
	 *            总行数的查询语句，也可以为一个整数字符串，表明总行数是多少，
	 *            如果为null或""，系统为自动根据sqlQuery字符串生产总行数的查询语句
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doPageQueryClass(Class<T> clazz, String sqlPrefix,
			String sqlQuery, Map<Integer, Object> vParameters,
			QueryMapNestOne2One[] queryMapNestList, int page, int perPage,
			PageBean pageUtils, String countSql) throws DbException {
		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);

		TInteger rsStart=new TInteger();
		TInteger rsEnd =new TInteger();
		String pageSql = this.getPagedSql(sqlQuery, args, page, perPage,
				pageUtils, countSql,rsStart,rsEnd);
		return this.doQueryClass(clazz, pageSql, vParameters,
				queryMapNestList, sqlPrefix, null, this.getAutoCommit(),
				true,rsStart.getValue(),rsEnd.getValue());
//		return this.doQueryClassOne2One(clazz, sqlPrefix, sqlQuery,
//				vParameters, queryMapNestList,true,rsStart.getValue(),rsStart.getValue());

	}

	/**
	 * 此方法操作完成后，会默认自动关闭底层连接，不需要调用DataBase.close()方法关闭 连接
	 * 
	 * @param <T>
	 * @param clazz
	 *            需映射主类的Class对象
	 * @param sqlPrefix
	 *            主类对应的sql前缀
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @param queryMapNestList
	 *            一到一映射关系
	 * @param page
	 *            当前页，从第一页开始
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            返回分页信息的类
	 * @param countSql
	 *            总行数的查询语句，也可以为一个整数字符串，表明总行数是多少，
	 *            如果为null或""，系统为自动根据sqlQuery字符串生产总行数的查询语句
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doPageQueryClassOne2One(Class<T> clazz,
			String sqlPrefix, String sqlQuery,
			Map<Integer, Object> vParameters,
			QueryMapNestOne2One[] queryMapNestList, int page, int perPage,
			PageBean pageUtils, String countSql) throws DbException {
		return this.doPageQueryClass(clazz, sqlPrefix, sqlQuery, vParameters,
				queryMapNestList, page, perPage, pageUtils, countSql);

	}

	/**
	 * 一对多映射的分页调用，此方法只适合于oracle或microsoft sql server2005以上版本的数据库
	 * 
	 * @param <T>
	 * @param clazz
	 *            主类的Class对象
	 * @param sqlPrefix
	 *            主类对应的sql前缀
	 * @param beanKey
	 *            主类的主属性，以英文逗号隔开
	 * @param sqlQuery
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @param queryMapNestList
	 *            一对多映射关系
	 * @param page
	 *            当前页
	 * @param perPage
	 *            每页多少行
	 * @param pageUtils
	 *            存放分页信息
	 * @param countSql
	 *            总行数的查询语句，也可以为一个整数字符串，表明总行数是多少，
	 *            如果为null或""，系统为自动根据sqlQuery字符串生产查询总行数的语句， 但生成的总数的语句性能可能会比较低
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doPageQueryClassOne2Many(Class<T> clazz,
			String sqlPrefix, String beanKey, String sqlQuery,
			Map<Integer, Object> vParameters,
			QueryMapNestOne2Many[] queryMapNestList, int page, int perPage,
			PageBean pageUtils, String countSql, String pageIdsSql)
			throws DbException {
		// DENSE_RANK() OVER(order by score desc) as drnk

		try {
			Collection<Object> args = CollectionUtils
					.getSortedValues(vParameters);
			TInteger rsStart=new TInteger();
			TInteger rsEnd =new TInteger();
			String sql = this.getPagedSqlByGroup(beanKey, sqlPrefix, sqlQuery,
					args, page, perPage, pageUtils, countSql,rsStart,rsEnd);

			return this.doQueryClassOne2Many(clazz, sql, beanKey,
					sqlQuery, vParameters, queryMapNestList);

		} catch (Exception ex) {
			throw new DbException(ex);
		}

	}

	/**
	 * 一到多关联映射查询。
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param clazz
	 *            需映射的主类Class对象
	 * @param sqlPrefix
	 *            sql前缀        
	 * @param beanKey
	 *            为主类对应的主键属性名， 如果主键为复合主键，以英文逗号隔开。
	 * @param sqlQuery
	 *            sql语句
	 *            <p>
	 *            <blockquote>
	 * 
	 *            <pre>
	 * String sql = &quot;select n.*,p.*,a.* from news n left outer join photos p&quot;
	 * 		+ &quot; on   n.id=p.newsid  left outer join archives a &quot;
	 * 		+ &quot; on n.id=a.newsid  where n.type=?  and n.audi=1 order by n.id desc&quot;;
	 *</pre>
	 * 
	 *            </blockquote>
	 *            </p>
	 * <br/>
	 *            如果主类对应于n表(news)，那么sql前缀为"n."
	 * @param vParameters
	 *            参数
	 * @param queryMapNestList
	 *            关联子类关系
	 * @return 返回查询结果
	 * @throws DbException
	 */
	public <T> List<T> doQueryClassOne2Many(Class<T> clazz, String sqlPrefix,
			String beanKey, String sqlQuery, Map<Integer, Object> vParameters,
			QueryMapNestOne2Many[] queryMapNestList) throws DbException {

		// for (int i = 0; i < queryMapNestList.length; i++) {
		// QueryMapNestOne2Many query2Many = queryMapNestList[i];
		// }
		List list = this.doQueryClass(clazz, sqlQuery, vParameters,
				queryMapNestList, sqlPrefix, beanKey, this.getAutoCommit());

		// log.debug("---------------------------");

		// 过滤，并进行子类赋值
		for (int i = 0; i < queryMapNestList.length; i++) {

			QueryMapNestOne2Many query2Many = queryMapNestList[i];
			String key = beanKey;
			String[] keyArray = key.split(";|,");
			keyArray = ArrayUtils.trim(keyArray);
			String nestBeanName = query2Many.getNestedBeanName();
			Map<String, List> result = query2Many.getResult();
			Set<String> keys = result.keySet();
			Iterator<String> iter = keys.iterator();

			while (iter.hasNext()) {
				String keyValue = iter.next();
				List childrenList = result.get(keyValue);

				// 找父亲类的主键值
				for (int m = 0; m < list.size(); m++) {
					Object bean = list.get(m);

					boolean b = BeanUtils.equals(bean, keyArray, keyValue);
					if (b) {
						try {
							// log.debug("keyValue="+keyValue+":"
							// +nestBeanName+":childrenList.size()="
							// +childrenList.size());

							PropertyUtils.setSimpleProperty(bean, nestBeanName,
									childrenList);
						} catch (Exception e) {
						}
						break;
					}
				}
			}
		}
		return list;

	}

	private <T> List<T> doQueryObject(String sqlQuery,
			Collection<Object> vParameters, RowMapper<T> rowMapper,
			boolean isPageQuery,int rsStart, int rsEnd)
			throws DbException {

		boolean close = this.getAutoCommit();

		List<T> results = new ArrayList();
		try {
			// Collection<Object> vParameters
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			DataBaseSet dbset = this.getResultSet(sqlQuery, vParameters);
			this.rs = dbset.getResultSet();

			if (isPageQuery) {
				if (rsStart <= 0)
					rs.beforeFirst();
				else
					rs.absolute(rsStart);
			} else {
				rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
			}
			int index = 0;
			int count = rsEnd -rsStart;
			T obj = null;

			while (dbset.next()) {
				if (isPageQuery) {
					if (index++ >= count) {
						break;
					}

				}
				obj = rowMapper.mapRow(dbset);
				results.add(obj);
			}
			return results;
		} catch (Exception e) {
			log.error("", e);
			throw new DbException("", e);
		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
	}

	/**
	 * 自定义映射查询
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 *            需映射的类
	 * @param sqlQuery
	 *            sql语句
	 * @param args
	 *            参数
	 * @param rowMapper
	 *            映射接口，用户可以通过此接口的回调函数来执行映射
	 * @return
	 * @throws DbException
	 */
	public <T> List<T> doQueryObject(String sqlQuery,
			Map<Integer, Object> args, RowMapper<T> rowMapper)
			throws DbException {

		Collection<Object> vParameters = CollectionUtils.getSortedValues(args);

		return this.doQueryObject(sqlQuery, vParameters, rowMapper,false,0,0);
		// Collection<Object> vParameters

	}

	private <T> List<T> doQueryClass(Class<T> clazz, String sqlQuery,
			Map<Integer, Object> vParameters,
			QueryMapNestOne2One[] queryMapNestList, String sqlPrefix,
			String beanKey, boolean close,
			boolean isPageQuery,int rsStart, int rsEnd) throws DbException {
		// return this.doQuery(clazz, sqlQuery, vParameters,
		// this.getAutoCommit());

		ArrayList<T> list = new ArrayList<T>();
		Map keyList = new LinkedHashMap();
		if (sqlPrefix == null)
			sqlPrefix = "";
		try {

			DataBaseSet rs = this.doQuery(sqlQuery, vParameters);

			this.rs = rs.getResultSet();

			if (isPageQuery) {

				if (rsStart <= 0)
					rs.beforeFirst();
				else
					rs.absolute(rsStart);
			} else {
				rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置
			}
			int index = 0;
			int count = rsEnd - rsStart;
			while (rs.next()) {
				try {

					if (isPageQuery) {

						if (index++ >= count) {
							break;
						}

					}
					T bean = clazz.newInstance();
					// Map<String, Object> nestedObjs = new HashMap<String,
					// Object>();
					Map<?, ?> map = PropertyUtils.describe(bean);
					Set<?> set = map.keySet();
					Iterator<?> i = set.iterator();

					while (i.hasNext()) {

						String name = (String) i.next();
						// System.err.println("bean:propertye:" + name);
						// Class t=PropertyUtils.getPropertyType(bean,name);
						// Object value=map.get(name);
						if (name.equals("class"))
							continue;

						Class<?> t = PropertyUtils.getPropertyType(bean, name);
						Object value = null;

						// name为javabean属性名
						if (SqlUtils.checkedSimpleType(t)) {// 简单类型

							value = SqlUtils.getValueFromResult(t, sqlPrefix,
									name, rs.getResultSet(), DataBaseKeyMap
											.getMap());
							if(value != null)
								PropertyUtils.setProperty(bean, name, value);
						} else {

							// 映射类型
							for (int m = 0; queryMapNestList != null
									&& m < queryMapNestList.length; m++) {

								MapNest queryMapNest = queryMapNestList[m];
								int mapRelation = queryMapNest.getMapRelation();
								String nestedBeanName = queryMapNest
										.getNestedBeanName();
								String prefix = queryMapNest.getPrefix();
								if (prefix == null)
									prefix = "";
								String[] toPros = queryMapNest.getToPros();

								if (mapRelation == MapNest.ONE_TO_ONE) {

									if (name.equals(nestedBeanName)) {
										Class<?> nestedClass = PropertyUtils
												.getPropertyType(bean,
														nestedBeanName);

										Object nestedObj = nestedClass
												.newInstance();

										nestedObj = BeanUtils.setOne2One(
												nestedObj, toPros, prefix, rs);
										PropertyUtils.setProperty(bean,
												nestedBeanName, nestedObj);

									} else {// 表明不进行映射的属性
										// throw new Exception(nestedBeanName
										// + " is not in " + clazz);
									}
								} else {// 表明不进行映射的属性

									throw new DbException("此方法不支持一到多管理映射！");
								}

							}
							// value = rs.getObject(name);

						}

					}// while

					if (beanKey == null) {
						list.add(bean);
					} else {
						String kv = BeanUtils.getKeyValue(bean, beanKey);
						keyList.put(kv, bean);
					}

				} catch (Exception e) {
					// log.error("", e);
					throw new DbException("", e);

				}
			}// while
		} catch (Exception e) {
			log.error("", e);
			throw new DbException("", e);

		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		if (beanKey == null) {
			return list;
		} else {
			Collection coValues = keyList.values();
			list.clear();
			list.addAll(coValues);
			return list;
		}

	}

	private <T> List<T> doQueryClass(Class<T> clazz, String sqlQuery,
			Map<Integer, Object> vParameters,
			QueryMapNestOne2Many[] queryMapNestList, String sqlPrefix,
			String beanKey, boolean close) throws DbException {
		// return this.doQuery(clazz, sqlQuery, vParameters,
		// this.getAutoCommit());

		ArrayList<T> list = new ArrayList<T>();
		Map keyList = new LinkedHashMap();
		if (sqlPrefix == null)
			sqlPrefix = "";
		try {

			DataBaseSet rs = this.doQuery(sqlQuery, vParameters);

			this.rs = rs.getResultSet();

			rs.beforeFirst(); // 如果还要用结果集，就把指针再移到初始化的位置

			while (rs.next()) {
				try {

					T bean = clazz.newInstance();
					// Map<String, Object> nestedObjs = new HashMap<String,
					// Object>();
					Map<?, ?> map = PropertyUtils.describe(bean);
					Set<?> set = map.keySet();
					Iterator<?> i = set.iterator();

					while (i.hasNext()) {

						String name = (String) i.next();
						// System.err.println("bean:propertye:" + name);
						// Class t=PropertyUtils.getPropertyType(bean,name);
						// Object value=map.get(name);
						if (name.equals("class"))
							continue;

						Class<?> t = PropertyUtils.getPropertyType(bean, name);
						Object value = null;

						// name为javabean属性名
						if (SqlUtils.checkedSimpleType(t)) {// 简单类型

							value = SqlUtils.getValueFromResult(t, sqlPrefix,
									name, rs.getResultSet(), DataBaseKeyMap
											.getMap());
							PropertyUtils.setProperty(bean, name, value);
						} else {

							// 映射类型
							for (int m = 0; queryMapNestList != null
									&& m < queryMapNestList.length; m++) {

								MapNest queryMapNest = queryMapNestList[m];
								int mapRelation = queryMapNest.getMapRelation();
								String nestedBeanName = queryMapNest
										.getNestedBeanName();
								String prefix = queryMapNest.getPrefix();
								if (prefix == null)
									prefix = "";
								String[] toPros = queryMapNest.getToPros();

								if (mapRelation == MapNest.ONE_TO_MANY) {
									if (name.equals(nestedBeanName)) {
										QueryMapNestOne2Many queryMapNestMany = (QueryMapNestOne2Many) queryMapNest;
										String parentKey = beanKey;
										Map<String, List> result = queryMapNestMany
												.getResult();
										String nestKey = queryMapNestMany
												.getNestedBeanKey();

										Class<?> nestedClass = queryMapNestMany
												.getClassType();
										Object nestedObj = nestedClass
												.newInstance();

										nestedObj = BeanUtils.setOne2One(
												nestedObj, toPros, prefix, rs);

										String parentKeyValue = BeanUtils
												.getKeyValue(bean, sqlPrefix,
														parentKey, rs);
										List nestlist = result
												.get(parentKeyValue);

										if (nestlist == null) {
											nestlist = new ArrayList();
											result
													.put(parentKeyValue,
															nestlist);
										}
										if (nestKey != null
												&& !nestKey.equals("")) {// //过滤重复的值

											String nestKeyValueStr = BeanUtils
													.getKeyValue(nestedObj,
															nestKey);

											if (nestKeyValueStr != null) {
												boolean find = false;
												for (Object obj : nestlist) {
													// Object obj =
													// nestlist.get(j);
													String nestKeyStr = BeanUtils
															.getKeyValue(obj,
																	nestKey);
													// log.debug("nestKeyStr="+nestKeyStr);
													if (nestKeyStr != null
															&& nestKeyStr
																	.equals(nestKeyValueStr)) {
														find = true;
														break;
													}

												}
												if (!find) {
													nestlist.add(nestedObj);
												}
											}
										} else {
											nestlist.add(nestedObj);
										}

									}
								} else {// 表明不进行映射的属性

									throw new DbException("此方法不支持一到一关联映射！");
								}

							}
							// value = rs.getObject(name);

						}

					}// while

					if (beanKey == null) {
						list.add(bean);
					} else {
						String kv = BeanUtils.getKeyValue(bean, beanKey);
						keyList.put(kv, bean);
					}

				} catch (Exception e) {
					// log.error("", e);
					throw new DbException("", e);

				}
			}// while
		} catch (Exception e) {
			log.error("", e);
			throw new DbException("", e);

		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		if (beanKey == null) {
			return list;
		} else {
			Collection coValues = keyList.values();
			list.clear();
			list.addAll(coValues);
			return list;
		}

	}

	private String trimTailOrderBy(String sql, TString orderStr) {
		sql = sql.trim();
		String newSql = "";
		String reg = "order\\s+by\\s+\\w+(\\s+(asc|desc))?";
		TInteger startPos = new TInteger();
		boolean isEnd = StringUtils.endsWith(sql, reg, true, startPos);
		if (isEnd) {
			orderStr.setValue(sql.substring(startPos.getValue()));
			newSql = sql.substring(0, startPos.getValue());
		} else {
			orderStr.setValue("");
			newSql = sql;
		}
		return newSql;
	}

	private String getCountSql(String sqlQuery) throws Exception {

		if (sqlQuery == null)
			return null;
		String sql = sqlQuery.trim();

		String maxSql = "";
		sqlQuery = sqlQuery.trim();

		sqlQuery = trimTailOrderBy(sqlQuery, new TString());

		StringBuilder sb = new StringBuilder(sqlQuery);
		if (!StringUtils.startsWithIgnoreCase(sqlQuery, "select")) {
			throw new DbException("语句不是select查询语句！");
		}
		if (this.dataBaseType == DataBaseType.MS_SQL_SERVER) {

			String regx = "select\\s+top";

			TInteger tend = new TInteger();
			boolean hasTop = StringUtils.startsWith(sb.toString(), regx, true);
			if (!hasTop) {
				int from = StringUtils.indexOf(sql, "from", true);
				maxSql = sb.replace(6, from, " count(*) ").toString();// select
				// is 6
				// characters
			} else {
				maxSql = "select count(*) from (" + sb.toString() + ") t";
			}

		} else {
			int from = StringUtils.indexOf(sql, "from", true);
			maxSql = sb.replace(6, from, " count(*) ").toString();// select is 6
			// characters
		}

		return maxSql;

	}

	private String getPagedSqlByGroup(String beanKey, String sqlPrefix,
			String sqlQuery, Collection<Object> vParameters, int page,
			int perPage, PageBean pageUtils, String countSql,TInteger rsStart,
			  TInteger rsEnd) throws Exception {

		// //dense_rank() over(order by score desc) as drnk

		// String key

		if (sqlQuery == null)
			return null;
		String sql = sqlQuery.trim();

		int total = 0;
		String maxSql = countSql;
		TString orderStr = new TString("");
		String tsql = this.trimTailOrderBy(sqlQuery, orderStr);
		beanKey = beanKey.trim();
		String[] keys = beanKey.split(",");

		maxSql = StringUtils.trim(maxSql);
		String reg = "\\d+";

		if (maxSql.matches(reg)) {// 说明是数字
			total = Integer.valueOf(maxSql);
		} else {// 说明是查询总行数的sql语句

			String tempSql = this.trimTailOrderBy(sqlQuery, new TString());
			if (StringUtils.isEmpty(maxSql)) {
				String groupby = ArrayUtils.toString(ArrayUtils.prefixAndNew(
						keys, "ta."), ",");
				maxSql = "select " + groupby + " from (" + tempSql
						+ ")ta group by " + groupby;
				maxSql = "select count(*) from (" + maxSql + ")f";

			} else {
				maxSql = tempSql;
			}

			RowSet rs = this.doCachedQuery(maxSql, vParameters, false);// 不关闭数据库连接
			while (rs != null && rs.next()) {
				total = rs.getInt(1);
				break;
			}
		}

		pageUtils.doPage(total, page, perPage);

		int begin = 0;
		int end = 0;

		if (this.dataBaseType == DataBaseType.MS_SQL_SERVER) {

			if (this.dataBaseMajorVersion <= 8) {// microsoft sql server
				// 2000或以下版本不支持
				throw new DbException("microsoft sql server 2000或以下版本不支持"
						+ "不支持一到多关系的此分页功能，只在oracle或"
						+ "microsoft sql server 2005以上版本的数据库支持！");
			}
			boolean startSelect = StringUtils.startsWith(sqlQuery, "select",
					true);
			if (startSelect) {
				StringBuilder sb = new StringBuilder(sqlQuery);
				String regx = "select\\s+top";
				TInteger tend = new TInteger();
				boolean hasTop = StringUtils.startsWith(sb.toString(), regx,
						true, tend);

				if (!hasTop) {

					tend.setValue(6);
				}

				String preKeysStr = ArrayUtils.toString(ArrayUtils
						.prefixAndNew(keys, sqlPrefix.trim()), ",");
				String denseRange = " dense_rank() over(order by " + preKeysStr
						+ " ) as rownumber ";
				sqlQuery = sb.insert(tend.getValue(), denseRange).toString();

			} else {
				return "";
			}
			sqlQuery = sqlQuery.trim();
			sql = "select * from ( " + sqlQuery.toString()
					+ ") t where t.rownumer>" + pageUtils.getStart() + ""
					+ " and t.rownumer<=" + pageUtils.getEnd();
			begin = 0;
			end = pageUtils.getEnd();

		} else if (this.dataBaseType == DataBaseType.ORACLE) {

			sqlQuery = sqlQuery.trim();

			String preKeysStr = ArrayUtils.toString(ArrayUtils.prefixAndNew(
					keys, "t."), ",");
			String denseRange = " dense_rank() over(order by " + preKeysStr
					+ " ) as rownumber";

			sql = "select xx.* from ( select t.*," + denseRange + " from ("
					+ sqlQuery + ")t )xx where rownumer>"
					+ pageUtils.getStart() + " and rownumer<="
					+ pageUtils.getEnd();
			begin = 0;
			end = pageUtils.getEnd();
		} else {

			throw new DbException("此数据库类型暂时不支持一到多关系的此分页功能，只在oracle或"
					+ "microsoft sql server 2005以上版本的数据库支持！");

		}
		// {"start":3,"end":4}
	
		rsStart.setValue(begin);
		rsEnd.setValue(end);

		return sql;

	}

	private String getPagedSql(String sqlQuery, Collection<Object> vParameters,
			int page, int perPage, PageBean pageUtils, String countSql,TInteger rsStart,
			  TInteger rsEnd)
			throws DbException {

		if (sqlQuery == null)
			return null;
		String sql = sqlQuery.trim();

		int total = 0;

		try {
			String maxSql = countSql;
			TString orderStr = new TString("");
			String tsql = this.trimTailOrderBy(sqlQuery, orderStr);
			//
			maxSql = StringUtils.trim(maxSql);
			String reg = "\\d+";
			if (maxSql.matches(reg)) {// 说明是数字
				total = Integer.valueOf(maxSql);
			} else {// 说明是查询总行数的sql语句
				if (StringUtils.isEmpty(maxSql)) {
					maxSql = this.getCountSql(tsql);

				} else {
					maxSql = this.trimTailOrderBy(maxSql, new TString());
				}

				RowSet rs = this.doCachedQuery(maxSql, vParameters, false);// 不关闭数据库连接
				while (rs != null && rs.next()) {
					total = rs.getInt(1);
					break;
				}
			}

			pageUtils.doPage(total, page, perPage);

			// int end = pageUtils.getEnd();
			int begin = 0;
			int end = 0;
			// DataBaseType dt = JdbcDriverMap.map.get(this.getDriverClass());
			if (this.dataBaseType == DataBaseType.MYSQL) {
				sql = sqlQuery.trim() + " limit " + pageUtils.getStart() + ","
						+ pageUtils.getPerPage();
				begin = 0;
				end = pageUtils.getEnd() - pageUtils.getStart();
			} else if (this.dataBaseType == DataBaseType.MS_SQL_SERVER) {
				if (this.dataBaseMajorVersion <= 8) {// microsoft sql server
					// 2000或以下版本
					sqlQuery = sqlQuery.trim();
					if (StringUtils.startsWithIgnoreCase(sqlQuery, "select")) {
						StringBuilder sb = new StringBuilder(sqlQuery);
						String regx = "select\\s+top";
						if (!StringUtils.startsWith(sb.toString(), regx, true)) {
							sqlQuery = sb.insert(6, " top 100 percent")
									.toString();
						}

					} else {
						return "";
					}
					sql = "select top " + pageUtils.getEnd() + " t.* from ("
							+ sqlQuery + ") t";
					begin = pageUtils.getStart();
					end = pageUtils.getEnd();
				} else { // microsoft sql server 2005或以上版本

					/*
					 * select * from( select top 3 ROW_NUMBER() OVER (order by
					 * id desc ,var1 desc) as row, * from test ) t where
					 * t.row>=2 and t.row<=3;
					 */

					boolean startSelect = StringUtils.startsWith(sqlQuery,
							"select", true);
					if (startSelect) {
						StringBuilder sb = new StringBuilder(sqlQuery);
						String regx = "select\\s+top";
						TInteger tend = new TInteger();
						boolean hasTop = StringUtils.startsWith(sb.toString(),
								regx, true, tend);
						if (StringUtils.isEmpty(orderStr.getValue())) {
							throw new DbException(
									"对于此数据库类型，你必须在sql语句最后指定order by子句，才能使用此分页查询功能！");
						}
						if (!hasTop) {

							sqlQuery = sb.insert(
									6,
									" top " + pageUtils.getEnd()
											+ "  row_number() over " + "("
											+ orderStr.getValue() + ") "
											+ "as rownumer,").toString();
						} else {

							sqlQuery = "select t.*,row_number() over" + "("
									+ orderStr.getValue()
									+ ")as rownumer from (" + sqlQuery + ")t";

						}

					} else {
						throw new DbException("指定的sql语句不是select查询语句！");
					}
					sqlQuery = sqlQuery.trim();
					sql = "select * from ( " + sqlQuery.toString()
							+ ") t where t.rownumer>" + pageUtils.getStart()
							+ "" + " and t.rownumer<=" + pageUtils.getEnd();
					begin = 0;
					end = pageUtils.getEnd();

				}

			} else if (this.dataBaseType == DataBaseType.ORACLE) {

				/*
				 * select xx.* from( select t.*,row_number() over(order by
				 * null)as num from (select * from test order by id) t )xx where
				 * num>5 and num<7;
				 */
				sqlQuery = sqlQuery.trim();
				// sql="select * from (select a.*,rownum rn from " +
				// "("+sqlQuery+") a where rownum<="+pageUtils.getEnd()+") " +
				// "where rn>"+pageUtils.getStart();
				if (StringUtils.isEmpty(orderStr.getValue())) {
					orderStr.setValue("order by null");
				}
				sql = "select xx.* from ( select t.*,row_number() over" + "("
						+ orderStr.getValue() + ")as rownumer from ("
						+ sqlQuery + ")t )xx where rownumer>"
						+ pageUtils.getStart() + " and rownumer<="
						+ pageUtils.getEnd();
				begin = 0;
				end = pageUtils.getEnd();
			} else {

				begin = pageUtils.getStart();
				end = pageUtils.getEnd();
				// throw new UnsupportedOperationException(" 此数据库类型不支持分页功能！");

			}
			// {"start":3,"end":4}
			
			rsStart.setValue(begin);
			rsEnd.setValue(end);

			return sql;

		} catch (Exception e) {
			log.error("", e);
			throw new DbException("", e);
		}
	}

	private CachedRowSet getCachedRowSet(ResultSet rs,boolean isPageQuery,
			int rsStart, int rsEnd) throws Exception {

		CachedRowSet crs = new CachedRowSetImpl();

		if (isPageQuery) {
			int begin = rsStart, end = rsEnd;
			crs.setMaxRows(end - begin);
			crs.populate(rs, begin + 1);
		} else {
			rs.beforeFirst();
			crs.populate(rs);
		}

		return crs;
	}

	private DataBaseSet getResultSet(String sqlQuery,
			Collection<Object> vParameters) throws Exception {

		PreparedStatement preStmt = this.getPreparedStatement(sqlQuery);

		String paramStr = SqlUtils.setToPreStatment(vParameters, preStmt);

		if (log.isDebugEnabled()) {
			String line = System.getProperty("line.separator");
			log.debug("sql [" + sqlQuery + "]" + line + "sql param[ "
					+ paramStr + " ]" + line + "debugSql["
					+ SqlUtils.generateNotParmatersSql(sqlQuery, vParameters)
					+ "]");

		}

		this.rs = preStmt.executeQuery();

		return new DataBaseSet(this.rs);
	}

	/**
	 * 不关闭底层数据库连接
	 * 
	 * @param sqlQuery
	 * @param vParameters
	 * @return
	 * @throws DbException
	 */
	private DataBaseSet doQuery(String sqlQuery, Collection<Object> vParameters)
			throws DbException {

		DataBaseSet result = null;
		try {

			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			result = this.getResultSet(sqlQuery, vParameters);

		} catch (Exception e) {
			log.error("", e);
			result = null;
			throw new DbException("");

		} finally {

		}

		return result;
	}

	/**
	 * 删除
	 * 
	 * @param sqltext
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return
	 * @throws DbException
	 */
	public int executeBindDelete(String sqltext,
			Map<Integer, Object> vParameters) throws DbException {

		return this.executeBindUpdate(sqltext, vParameters);

	}

	/**
	 * 更新操作（包括删除）
	 * 
	 * @param sqltext
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return
	 * @throws DbException
	 */
	public int executeBindUpdate(String sqltext,
			Map<Integer, Object> vParameters) throws DbException {

		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return this.executeBindUpdate(sqltext, args);

	}

	/**
	 * 调用存储过程
	 * 
	 * @param sqltext
	 *            sql语句
	 * @param parms
	 *            用法举例如下：
	 *            <p>
	 *            <blockquote>
	 * 
	 *            <pre>
	 *            用法：
	 *            parms.put("1","中");//默认为in类型 
	 *            parms.put("2:in","孙");
	 *            parms.put("3:in",new Integer(3));
	 *            parms.put("4:out",int.class);
	 *            parms.put("5:out",java.util.data.class);
	 *            parms.put("6:inout",new Long(44));
	 * </pre>
	 * 
	 *            </blockquote>
	 *            </p>
	 * @param outPramsValues
	 *            存放输出参数的值
	 * @param returnDataBaseSets
	 *            需返回值的结果集
	 * @return
	 * @throws DbException
	 */

	public int executeStoredProcedure(String sqltext,
			Map<String, Object> parms, Map<Integer, Object> outPramsValues,
			List<DataBaseSet> returnDataBaseSets) throws DbException {
		boolean close = true;
		try {

			close = this.getAutoCommit();

			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			// List<DataBaseSet> returnSets = new ArrayList<DataBaseSet>();

			Set<String> keys = parms.keySet();
			List<String> keyList = new ArrayList<String>(keys);
			// Collection<String> sss=new ArrayList<String>();
			// list.addAll(keys);
			Collections.sort(keyList, new Comparator<String>() {

				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					// return 0;
					String[] ss1 = o1.split(":|：");
					Assert.notEmpty(ss1);
					String[] ss2 = o1.split(":|：");
					Assert.notEmpty(ss2);

					return ss1[0].trim().compareTo(ss2[0].trim());
				}

			});

			Map<Integer, Object> inParms = new HashMap<Integer, Object>();
			Map<Integer, Object> outParms = new HashMap<Integer, Object>();

			for (int i = 0; i < keyList.size(); i++) {// 分离输入参数和输出参数
				String key = keyList.get(i);
				String[] ss = key.split(":|：");

				Assert.state(ss != null && ss[0].matches("\\d+"));

				if (ss[1].trim().equalsIgnoreCase("in") || ss.length == 1) {
					inParms.put(Integer.valueOf(ss[0].trim()), parms.get(key));
				} else if (ss[1].trim().equalsIgnoreCase("inout")) {
					inParms.put(Integer.valueOf(ss[0].trim()), parms.get(key));
					outParms.put(Integer.valueOf(ss[0].trim()), parms.get(key));
				} else if (ss[1].trim().equalsIgnoreCase("out")) {
					outParms.put(Integer.valueOf(ss[0].trim()), parms.get(key));
				} else {
					log.error("int paramaters:" + ss[1] + " 不能解析!");
					throw new DbException("int paramaters:" + ss[1] + " 不能解析!");
				}
			}

			// this.cs = this.conn.prepareCall(sqltext);
			CallableStatement cs = this.getCallableStatement(sqltext);
			// 设置输入参数
			String inParamStr = SqlUtils.setToPreStatment(inParms, cs);

			// 注册输出参数
			String outParamStr = SqlUtils.registForStoredProc(outParms, cs);

			boolean flag = cs.execute();

			ResultSet rs = null;
			int updateCount = -1;
			do {
				updateCount = cs.getUpdateCount();
				if (updateCount != -1) {// 说明当前行是一个更新计数
					// 不进行处理.
					cs.getMoreResults();
					continue;// 已经是更新计数了,处理完成后应该移动到下一行,不再判断是否是ResultSet
				}
				rs = cs.getResultSet();
				if (rs != null) {// 如果到了这里,说明updateCount == -1
					// 处理rs
					CachedRowSet crs = new CachedRowSetImpl();
					try {
						rs.beforeFirst();
						crs.populate(rs);
					} finally {
						rs.close();
					}
					returnDataBaseSets.add(new DataBaseSet(crs));
					cs.getMoreResults();
					continue;
					// 是结果集,处理完成后应该移动到下一行
				}
				// 如果到了这里,说明updateCount == -1 && rs == null,什么也没的了

			} while (!(updateCount == -1 && rs == null));

			// 取输出参数

			SqlUtils
					.getValueFromCallableStatement(cs, outParms, outPramsValues);

		} catch (Exception e) {
			log.error(e);
			throw new DbException(e);
		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		return 0;
	}

	/**
	 * 返回更新的记录行的个数
	 * 
	 * @param sqltext
	 * @param vParameters
	 * @return
	 */
	private int executeBindUpdate(String sqltext, Collection<Object> vParameters)
			throws DbException {

		int[] twoInt = this.executeBindUpdate(sqltext, vParameters, this
				.getAutoCommit());
		return twoInt[0];
	}

	private int[] executeBindUpdate(String sqltext,
			Collection<Object> vParameters, boolean close) throws DbException {

		int count = 0;
		int[] twoInt = new int[] { -1, -1 };
		try {
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}

			boolean isInsert = false;
			PreparedStatement preStmt = null;

			sqltext = sqltext.trim();
			String head = sqltext.substring(0, 6);
			if ("insert".equalsIgnoreCase(head.trim())) {
				isInsert = true;
			}

			preStmt = this.getPreparedStatement(sqltext);

			String paramStr = SqlUtils.setToPreStatment(vParameters, preStmt);

			if (log.isDebugEnabled()) {
				String line = System.getProperty("line.separator");
				log.debug("sql ["
						+ sqltext
						+ "]"
						+ line
						+ "sql param[ "
						+ paramStr
						+ " ]"
						+ line
						+ "debugSql["
						+ SqlUtils
								.generateNotParmatersSql(sqltext, vParameters)
						+ "]");

			}
			count = preStmt.executeUpdate();

			twoInt[0] = count;
			boolean flag = false;
			// if (this.dataBaseType == DataBaseType.MYSQL) {
			// flag = true;
			// } else if (this.dataBaseType == DataBaseType.MS_SQL_SERVER) {
			// if (this.dataBaseDriverVersion >= 2) {
			// flag = true;
			// }
			//
			// }

			try {

				if (this.sqlType == DataBase.SQLType.INSERT) {
					ResultSet keys = preStmt.getGeneratedKeys();
					if (keys.next()) {
						twoInt[1] = keys.getInt(1);
					} else {
						twoInt[1] = -1;
					}
					keys.close();
				} else {
					twoInt[1] = -1;
				}

			} catch (Throwable e) {
				twoInt[1] = -1;
				log
						.error("您的驱动程序不支持生成主键的操作（PreparedStatement.getGeneratedKeys()），建议更换您的数据库驱动程序版本"
								+ "使之支持jdbc3.0标准！返回-1");
			}

		} catch (Exception e) {
			twoInt[0] = -1;
			twoInt[1] = -1;
			log.error("", e);

			throw new DbException(e);

		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		return twoInt;
	}

	/**
	 * 返回插入记录的行数
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param sqltext
	 *            sql语句
	 * @param vParameters
	 *            参数
	 * @return
	 */
	public int executeBindInsert(String sqltext,
			Map<Integer, Object> vParameters) throws DbException {

		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return this.executeBindInsert(sqltext, args);

	}

	/**
	 * 使insertObject对象的所有属性插入到数据库，如果insertObject的对象某个属性值为null，
	 * 那么会忽略此属性，不会插入空值到数据库。
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param insertObject
	 *            要插入的对象
	 * @return
	 * @throws DbException
	 */
	public <T> int excuteInsertClass(T insertObject) throws DbException {

		return this.excuteInsertClass(insertObject, null);

	}

	/**
	 * 向数据库插入在insertObject里properties数组指定的属性，如果在properties中的某
	 * 个属性对应insertObject属性值为空，那么会忽略此属性，不会插入空值到数据库。
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param insertObject
	 * @param properties
	 *            指定insertObject里需插入的属性，如果properties指定为空， 则插入insertObject对象所有属性
	 * @return
	 * @throws DbException
	 */
	public <T> int excuteInsertClass(T insertObject, String[] properties)
			throws DbException {

		Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
		try {
			String sqltext = SqlUtils.generateInsertSql(properties,
					insertObject, vParameters, DataBaseKeyMap.getMap());

			return this.executeBindInsert(sqltext, vParameters);

		} catch (Exception e) {
			throw new DbException(e);
		}

	}

	/**
	 * 把某对象的指定的属性插入到数据库到数据库，并返回主键，有些数据库的驱动程序不会返回主键，所以 要根据具体数据库而言，mysql数据可以返回主键。
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param type
	 * @param insertObject
	 * @return 返回插入的主键
	 * @throws DbException
	 */
	public <T> int excuteInsertClassReturnKey(T insertObject,
			String[] properties) throws DbException {

		Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
		try {
			String sqltext = SqlUtils.generateInsertSql(properties,
					insertObject, vParameters, DataBaseKeyMap.getMap());

			return this.executeBindInsertReturnKey(sqltext, vParameters);

		} catch (Exception e) {
			log.error("", e);
			throw new DbException(e);
		}

	}

	/**
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param insertObject
	 *            要插入的对象
	 * @return
	 * @throws DbException
	 */
	public <T> int excuteInsertClassReturnKey(T insertObject)
			throws DbException {

		return this.excuteInsertClassReturnKey(insertObject, null);

	}

	/**
	 * 插入多个对象的指定属性,此方法必须保证insertObjects是相同的类型
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param insertObjects
	 *            待插入的对象
	 * @param properties
	 *            对象的属性
	 * @return
	 * @throws DbException
	 */
	public <T> int[] excuteInsertClass(T[] insertObjects, String[] properties)
			throws DbException {

		String[][] pros = new String[insertObjects.length][];
		ArrayUtils.fill(pros, properties);
		return this.excuteInsertObjects(insertObjects, pros);
	}

	/**
	 * 插入多个对象指定的属性到数据库，insertObjects里的每个对象对应一个属性数组，所以为二维数组
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param insertObjects
	 * @param properties
	 * @return
	 * @throws DbException
	 */
	public int[] excuteInsertObjects(Object[] insertObjects,
			String[][] properties) throws DbException {

		int length = insertObjects.length;
		Map[] maps = new HashMap[length];
		String[] sqltexts = new String[length];
		try {
			for (int i = 0; i < maps.length; i++) {

				Object obj = insertObjects[i];
				Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
				String[] pros = null;
				if (properties != null)
					pros = properties[i];
				String sql = SqlUtils.generateInsertSql(pros, obj, vParameters,
						DataBaseKeyMap.getMap());

				maps[i] = vParameters;
				sqltexts[i] = sql;
			}

			int[] res = this.executeBindBatch(sqltexts, maps);

			return res;
		} catch (Exception e) {
			log.error("", e);
			throw new DbException(e);
		}
	}

	/**
	 *插入多个对象的所有属性到数据库，如果对象里某个属性为空，会忽略此属性
	 *<p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param insertObjects
	 *            待插入的对象，可以为不同类型的类
	 * @return
	 * @throws DbException
	 */
	public int[] excuteInsertObjects(Object[] insertObjects) throws DbException {
		return this.excuteInsertObjects(insertObjects, (String[][]) null);
	}

	/**
	 * 插入多个对象到数据库，如果对象里某个属性为空，会忽略此属性
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param objs
	 *            待插入的对象，所有对象的类型必须相同
	 * @return
	 * @throws DbException
	 */
	public <T> int[] excuteInsertClass(T[] objs) throws DbException {
		return this.excuteInsertClass(objs, (String[]) null);
	}

	/**
	 * 更新对象
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param updateObject
	 *            待更新的对象
	 * @param beanKey
	 *            待更新对象的主键属性名称，复合主键属性用逗号隔开
	 * @param properties
	 *            如果指定的属性在upateObject对象里的值为null，则忽略
	 * @return
	 * @throws DbException
	 */
	public <T> int excuteUpdateClass(T updateObject, String beanKey,
			String[] properties) throws DbException {

		Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
		try {
			String sqltext = SqlUtils
					.generateUpdateSql(properties, updateObject, beanKey,
							vParameters, DataBaseKeyMap.getMap());

			return this.executeBindUpdate(sqltext, vParameters);

		} catch (Exception e) {
			throw new DbException(e);
		}

	}

	/**
	 * 删除多个对象，所有对象都以deleteProperteis里指定的属性的值来定位删除
	 * 
	 * @param <T>
	 * @param deleteObject
	 * 
	 * @param deleteProperteis
	 *            复合属性（即以英文逗号隔开的属性） 待删除对象根据deleteProperteis字符串里的属性来定位删除。
	 *            deleteProperteis可以为多个属性，以英文逗号隔开
	 *            ，例如deleteProperteis为"name,id,age"，将会生产
	 *            "where name=? and id=? and age=?"条件。
	 * @return 返回int数组里每个元素值对应于删除每个对象时实际删除的行数， 如果数组元素的值为-1表示删除失败。
	 *         如果返回为null，表示执行失败
	 * @throws DbException
	 */
	public <T> int[] excuteDeleteClass(T[] deleteObject, String deleteProperteis)
			throws DbException {
		String[] strArray = new String[deleteObject.length];
		strArray = ArrayUtils.fill(strArray, deleteProperteis);
		return this.excuteDeleteObjects(deleteObject, strArray);

	}

	/**
	 * 删除一个对象，所有对象都以deleteProperteis属性的值来定位删除
	 * 
	 * @param <T>
	 * @param deleteObject
	 *            待删除的对象
	 * @param deleteProperteis
	 *            待删除对象根据deleteProperteis里的属性来定位删除。
	 *            deleteProperteis可以为多个属性，以英文逗号隔开
	 *            ，例如deleteProperteis为"name,id,age"，将会生产
	 *            "where name=? and id=? and age=?"条件。
	 * @return 返回实际更新的行数
	 * @throws DbException
	 */
	public <T> int excuteDeleteClass(T deleteObject, String deleteProperteis)
			throws DbException {
		Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
		try {
			String sql = SqlUtils.generateDeleteSql(deleteObject,
					deleteProperteis, vParameters, DataBaseKeyMap.getMap());

			return this.executeBindInsert(sql, vParameters);

		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	/**
	 * 把对象所有属性更新到数据库，如果某个属性值为null，则忽略
	 * <p>
	 * beanKey为对象主键属性（复合主键属性以逗号分开）
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param updateObject
	 *            待更新到数据库的对象
	 * @param beanKey
	 *            updateObject对象的主键属性，复合主键属性以英文逗号隔开
	 * @return
	 * @throws DbException
	 */
	public <T> int excuteUpdateClass(T updateObject, String beanKey)
			throws DbException {
		return this.excuteUpdateClass(updateObject, beanKey, (String[]) null);
	}

	/**
	 * 把对象指定属性更新到数据库
	 * <p>
	 * beanKey为对象主键属性（复合主键属性以逗号分开）
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param <T>
	 * @param objects
	 *            待更新到数据库的对象，对象数组里的类型必须一致，每个对象对应相同的properties数组
	 * @param beanKey
	 *            objects对象的主键属性，复合主键属性以英文逗号隔开
	 * @param properties
	 *            指定需更新的属性，如果指定的某个属性在对应对象里值为null，则忽略
	 * @return
	 * @throws DbException
	 */
	public <T> int[] excuteUpdateClass(T[] objects, String beanKey,
			String[] properties) throws DbException {

		int length = objects.length;
		String[] beanKeys = new String[length];
		ArrayUtils.fill(beanKeys, beanKey);
		String[][] pros = new String[length][];
		ArrayUtils.fill(pros, properties);
		return this.excuteUpdateObjects(objects, beanKeys, pros);

	}

	/**
	 * 把多个对象插入数据库，各个对象的类型可以不一样
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param objects
	 *            待更新到数据库的多个对象
	 * @param beanKeys
	 *            每个对象分别对应主键属性名
	 * @param properties
	 *            每个对象分别对应的待更新的属性，是个二维数组，每个对象对应一个数组，表明此对象需要更新的属性，
	 *            如果指定的某个属性在对应对象里值为null，则忽略。
	 * @return
	 * @throws DbException
	 */
	public int[] excuteUpdateObjects(Object[] objects, String[] beanKeys,
			String[][] properties) throws DbException {

		int length = objects.length;
		Map[] maps = new HashMap[length];
		String[] sqltexts = new String[length];
		try {
			for (int i = 0; i < maps.length; i++) {

				Object obj = objects[i];
				String key = beanKeys[i];
				Map<Integer, Object> vParameters = new HashMap<Integer, Object>();
				String[] pros = null;
				if (properties != null)
					pros = properties[i];
				String sql = SqlUtils.generateUpdateSql(pros, obj, key,
						vParameters, DataBaseKeyMap.getMap());

				maps[i] = vParameters;
				sqltexts[i] = sql;
			}

			int[] res = this.executeBindBatch(sqltexts, maps);

			return res;
		} catch (Exception e) {
			log.error("", e);
			throw new DbException(e);
		}
	}

	/**
	 * 删除多个对象
	 * 
	 * @param deleteObjects
	 *            待删除的多个对象，数组里的每个对象类型可以不相同
	 * @param deletePropertiesArray
	 *            每个对象对应一个复合属性，每个对象根据一个复合属性，来生产sql删除语句，每个复合属性以英文逗号隔开。
	 * @return 返回int数组里每个元素值对应于删除每个对象时实际删除的行数， 如果数组元素的值为-1表示删除失败。
	 *         如果返回为null，表示执行失败
	 * @throws DbException
	 */
	public int[] excuteDeleteObjects(Object[] deleteObjects,
			String[] deletePropertiesArray) throws DbException {

		int length = deleteObjects.length;
		Map[] maps = new HashMap[length];
		String[] sqltexts = new String[length];
		try {
			for (int i = 0; i < maps.length; i++) {

				Object obj = deleteObjects[i];
				String deleteProperties = deletePropertiesArray[i];
				Map<Integer, Object> vParameters = new HashMap<Integer, Object>();

				String sql = SqlUtils.generateDeleteSql(obj, deleteProperties,
						vParameters, DataBaseKeyMap.getMap());

				maps[i] = vParameters;
				sqltexts[i] = sql;
			}

			int[] res = this.executeBindBatch(sqltexts, maps);

			return res;
		} catch (Exception e) {
			log.error("", e);
			throw new DbException(e);
		}
	}

	/**
	 * 根据sql语句和参数，插入记录到数据库，返回插入记录的行数
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param sqltext
	 * @param vParameters
	 * @return 返回插入记录的行数，-1表明插入出错
	 */
	private int executeBindInsert(String sqltext, Collection<Object> vParameters)
			throws DbException {
		int[] twoInt = this.executeBindUpdate(sqltext, vParameters, this
				.getAutoCommit());
		return twoInt[0];
	}

	/**
	 * 如果insert一条语句用此函数，并返回插入数据库后返回此记录的主键
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param sqltext
	 * @param vParameters
	 * @return
	 */
	public int executeBindInsertReturnKey(String sqltext,
			Map<Integer, Object> vParameters) throws DbException {

		Collection<Object> args = CollectionUtils.getSortedValues(vParameters);
		return this.executeBindOneInsert(sqltext, args);
	}

	private int executeBindOneInsert(String sqltext,
			Collection<Object> vParameters) throws DbException {
		int[] twoInt = this.executeBindUpdate(sqltext, vParameters, this
				.getAutoCommit());
		return twoInt[1];
	}

	/**
	 * 设置是否为事务操作，false表明为事务操作（事务分为常规事务和分布式事务），事务操作即多个语句功能一个数据库连接。通过DataBase.
	 * setAutoCommit()方法
	 * 可以设置是否为事务操作，如果为事物操作，那么DataBase里所有默认自动关闭底层数据库连接的方法，都不会自动关闭
	 * 底层数据库连接，同一个事务里的所有方法共享一个数据库连接。用户必须手动通过DataBase.close()方法关闭数据库连接
	 * 
	 * @return
	 * @throws DbException
	 */
	public void setAutoCommit(boolean b) throws DbException {
		this.isAutoCommit = b;
		this.setInteralConnectionAutoCommit(b);
	}

	/**
	 * <pre>
	 *   重新连接数据库，当数据库连接关闭时，可以调用此方法进行重连。如果嫌每次重连太麻烦，你也可以通过设置
	 *   {@link #setAutoReconnect(boolean)}方法，通过传入参数为true，来让操作关闭后自动重连。
	 * 
	 * </pre>
	 * 
	 * @throws DbException
	 */
	public void reConnectDb() throws DbException {

		ConnectType ct = this.connectType;
		if (ct == ConnectType.POOL) {
			this.connectDb(this.dbPoolName);
		} else if (ct == ConnectType.CONNECT) {
			this.connectDb(this.dbUser, this.dbPass, this.dbUrl,
					this.driverClass);
		} else if (ct == ConnectType.JNDI) {
			this.connectJNDI(this.jndiPoolName);
		} else if (ct == ConnectType.DATASOURCE) {
			this.connectDb(this.dataSource, this.fromDistributeTransaction);
		} else {
			throw new DbException("您的数据库连接方式不支持重连！");
		}

	}

	/**
	 * 返回是否为事务操作，false表明为事务操作，事务操作即多个语句功能一个数据库连接。通过DataBase.setAutoCommit()方法
	 * 可以设置是否为事务操作，如果为事物操作，那么DataBase里所有默认自动关闭底层数据库连接的方法，都不会自动关闭
	 * 底层数据库连接，同一个事务里的所有方法共享一个数据库连接。用户必须手动通过DataBase.close()方法关闭数据库连接
	 * 
	 * @return
	 * @throws DbException
	 */
	public boolean getAutoCommit() throws DbException {
		try {
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			return this.isAutoCommit;
		} catch (Exception e) {
			log.error("", e);
			throw new DbException(e);
		}
	}

	/**
	 * 用于事务性操作的回滚，如果事务为分布式事务，则为空操作。
	 * 
	 * @throws DbException
	 */
	public void rollback() throws DbException {
		try {
			if (this.fromDistributeTransaction) {
				return;
			}
			conn.rollback();
		} catch (SQLException e) {
			log.error("", e);
			throw new DbException("" + e.getErrorCode() + "" + e);
		}
	}

	/**
	 * 判断资源和底层数据库连接是否关闭
	 * 
	 * @return
	 * @throws DbException
	 */
	public boolean isColsed() throws DbException {
		boolean result = false;
		try {
			if (this.conn == null || this.conn.isClosed()) {
				return true;
			}
		} catch (Exception e) {
			throw new DbException("error in isClosed!");
		}
		return result;
	}

	/**
	 * 事务性操作的事务的提交，当 {@link #setAutoCommit(boolean)}设为false，
	 * 会用到此方法，一般对于事务性操作会用到，如果 事务为分布式事务，则为空操作。
	 * 
	 * @throws DbException
	 */
	public void commit() throws DbException {
		try {
			if (this.fromDistributeTransaction) {
				return;
			}
			conn.commit();
		} catch (SQLException e) {
			log.error("", e);
			throw new DbException("" + e.getErrorCode() + "" + e);
		}
	}

	/**
	 * 
	 * 
	 * @param sqltext
	 * @param vParametersArray
	 * @param close
	 *            执行后是否关闭数据库
	 * @return 返回每条语句更新记录的条数，执行错误，会抛出异常
	 * @throws DbException
	 */
	private int[] executeBindBatch(String sqltext,
			List<Collection<Object>> vParametersArray, boolean close)
			throws DbException {

		int[] count = null;
		try {
			// boolean bkclolse=this.conn.getAutoCommit();
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			if (close) {
				this.setInteralConnectionAutoCommit(false);
				// conn.setAutoCommit(false);
			}

			PreparedStatement preStmt = null;

			sqltext = sqltext.trim();

			preStmt = this.getPreparedStatement(sqltext);

			for (int m = 0; m < vParametersArray.size(); m++) {
				Collection<Object> vParameters = vParametersArray.get(m);
				String paramStr = SqlUtils.setToPreStatment(vParameters,
						preStmt);

				if (log.isDebugEnabled()) {
					String line = System.getProperty("line.separator");
					// log.debug("sql:[" + sqltext + "]" + line + "sql param: "
					// + paramStr);

					log.debug("sql ["
							+ sqltext
							+ "]"
							+ line
							+ "sql param[ "
							+ paramStr
							+ " ]"
							+ line
							+ "debugSql["
							+ SqlUtils.generateNotParmatersSql(sqltext,
									vParameters) + "]");

				}
				preStmt.addBatch();
			}// for

			boolean error = false;
			count = preStmt.executeBatch();
			int i = 0;
			for (i = 0; i < count.length; i++) {
				if (count[i] == 0) {
					error = true;
					break;
				}
			}
			if (close) {
				if (error) {
					this.rollback();

					count = null;
					throw new DbException("执行错误,!第[" + i + "]条语句没有执行更新!");
				} else {
					this.commit();
				}
			}

		} catch (Exception e) {
			count = null;
			log.error("", e);
			if (close) {
				this.rollback();
			}
			throw new DbException(e);

		} finally {
			try {
				if (this.conn != null) {
					if (close) {
						this.close();
					} else {
						this.closer();
					}
				}
			} catch (Exception e2) {
				log.error("", e2);
			}
		}
		return count;
	}

	/**
	 * 返回的数组里每个值对应返回对应sql语句执行后更新的行数，如果为null表明执行失败，内部会自动回滚。
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param sqltxts
	 * @param vParametersList
	 * @return 如果为null表明执行失败
	 * @throws DbException
	 */
	public int[] executeBindBatch(String[] sqltxts,
			Map<Integer, Object>[] vParametersArray) throws DbException {

		int[] res = null;
		if (ArrayUtils.isNotEmpty(vParametersArray)) {
			if (sqltxts.length != vParametersArray.length) {
				throw new DbException("sqltexs的长度应该和vParametersArray的长度相等！");
			}
			res = new int[sqltxts.length];
		}
		boolean bkCommit = true;
		boolean error = false;
		try {
			bkCommit = this.getAutoCommit();
			if (bkCommit) {
				this.setInteralConnectionAutoCommit(false);
			}

			for (int i = 0; i < sqltxts.length; i++) {
				Collection<Object> args = null;
				if (ArrayUtils.isNotEmpty(res)) {
					args = CollectionUtils.getSortedValues(vParametersArray[i]);
				}
				int[] twoInt = this.executeBindUpdate(sqltxts[i], args, this
						.getAutoCommit());
				int ret = twoInt[0];// 返回记录的行数
				res[i] = ret;
				if (ret == 0) {
					error = true;
					break;
				}
			}

			if (bkCommit) {
				if (error) {
					this.rollback();
					res = null;
				} else {
					this.commit();
				}
			}
			return res;
		} catch (Exception ex) {
			res = null;
			log.error("", ex);
			try {
				if (bkCommit) {
					this.rollback();
				}
			} catch (DbException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
			throw new DbException(ex);

		} finally {
			try {
				if (bkCommit) {
					this.close();
				} else {
					this.closer();
				}
			} catch (DbException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}

		}

	}

	/**
	 * 批量更新操作（增，删，改），返回每条语句更新记录的行数
	 * <p>
	 * 此方法会默认自动关闭底层数据库连接，所以不需要 调用DataBase.close()方法
	 * 
	 * @param sqltxt
	 *            执行的sql语句
	 * @param vParametersList
	 *            每条语句所携带的参数，每条语句对应一个Map，每个Map存放相应语句的参数
	 * @return 返回每条语句更新记录的行数，执行错误，会抛出异常
	 * @throws DbException
	 */
	public int[] executeBindBatch(String sqltxt,
			List<Map<Integer, Object>> vParametersList) throws DbException {

		List<Collection<Object>> argsList = new ArrayList<Collection<Object>>();
		for (int i = 0; i < vParametersList.size(); i++) {
			Map<Integer, Object> map = vParametersList.get(i);
			Collection<Object> args = CollectionUtils.getSortedValues(map);
			argsList.add(args);
		}
	
		return executeBindBatch(sqltxt, argsList, this.getAutoCommit());
	}

	/**
	 * 不带参数的批量处理
	 * 
	 * @param sqltxt
	 * @return
	 * @throws DbException
	 */
	public int[] executeBatch(ArrayList<String> sqltxt) throws DbException {
		return updateBatch(sqltxt, this.getAutoCommit());
	}

	private int[] updateBatch(ArrayList<String> sqltxt, boolean close)
			throws DbException {
		int i;
		int[] ints = null;

		try {
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			if (close) {
				this.setInteralConnectionAutoCommit(false);
				// this.setAutoCommit(false);
			}
			stat = conn.createStatement();

			String line = System.getProperty("line.separator");
			String sql = "sql[" + line;
			for (i = 0; i < sqltxt.size(); i++) {
				// log.info(sqltxt[i]);
				if (sqltxt.get(i) != null && !(sqltxt.get(i).trim()).equals("")) {

					if (log.isDebugEnabled()) {
						sql = sql + sqltxt.get(i) + "" + line;

					}
					stat.addBatch(sqltxt.get(i));
				}
			}
			sql = sql + "]";
			log.debug(sql);
			try {
				boolean error = false;
				ints = stat.executeBatch();
				for (int m = 0; m < ints.length; m++) {
					if (ints[m] == 0) {
						error = true;
						break;
					}
				}
				if (close) {
					if (error) {
						this.rollback();
						ints = null;
					} else {
						this.commit();
					}
				}

			} catch (BatchUpdateException e) {
				ints = null;
				log.error("", e);
				int[] counts = e.getUpdateCounts();

				throw new SQLException("{" + counts.length + ""
						+ e.getErrorCode() + "" + e);
			}
		} catch (SQLException e) {
			log.error("", e);
			ints = null;
			if (close) {
				this.rollback();
			}
			throw new DbException("" + e.getErrorCode() + "" + e);
		} finally {
			try {
				if (close) {
					this.close();
				} else {
					this.closer();
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return ints;
	}

	private CallableStatement getCallableStatement(String sqltext)
			throws Exception {
		this.sqlType = DataBase.SQLType.STORE_DPROCEDURE;
		this.cs = this.conn.prepareCall(sqltext);
		return this.cs;
	}

	private PreparedStatement getPreparedStatement(String sqltxt)
			throws DbException {
		try {
			if (this.conn == null || this.conn.isClosed()) {
				if (this.isAutoReconnect()) {
					log.debug("reconnect database!");
					this.reConnectDb();
				} else {
					throw new DbException("connection is null!");
				}
			}
			if (StringUtils.startsWithIgnoreCase(sqltxt.trim(), "select")) {
				try {
					this.sqlType = DataBase.SQLType.SELECT;
					ps = conn.prepareStatement(sqltxt,
							ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
				} catch (Throwable e) {
					log.error("您的数据库驱动程序版本过低，请更新成支持jdbc3.0以上版本的驱动！");
				}
			} else if (StringUtils
					.startsWithIgnoreCase(sqltxt.trim(), "insert")) {
				try {
					this.sqlType = DataBase.SQLType.INSERT;
					ps = conn.prepareStatement(sqltxt,
							Statement.RETURN_GENERATED_KEYS);
				} catch (Throwable e) {
					log
							.warn("您的数据库驱动程序不支持带Statement.RETURN_GENERATED_KEYS参数"
									+ "的PreparedStatement构造函数，可能由于您的数据库版本较低，建议升级您的驱动程序！");
					ps = conn.prepareStatement(sqltxt);
				}
			} else {
				this.sqlType = DataBase.SQLType.UPDATE;
				ps = conn.prepareStatement(sqltxt);
			}

		} catch (SQLException e) {
			log.error("", e);
			throw new DbException("" + e.getErrorCode() + "" + e);
		}
		return ps;
	}

	/**
	 * 关闭底层资源，但不关闭数据库连接
	 */
	public void closer() throws DbException {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
				// log.info("----------state.close-");
			}
			if (cs != null) {
				cs.close();
				cs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;

			}

		} catch (SQLException e) {
			log.error("", e);
			throw new DbException("" + e.getErrorCode() + "" + e);
		}
	}

	/**
	 * 关闭数据库连接，释放底层占用资源
	 */
	public void close() {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (cs != null) {
				cs.close();
				cs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {

				if (!conn.isClosed()) {
					try {
						this.setInteralConnectionAutoCommit(true);
					} catch (Exception ex) {
						log.error("", ex);
					}
					conn.close();
				}
				conn = null;
			}

		} catch (SQLException e) {
			log.error("", e);
		}
	}

	public static void main(String[] args) {
		// log.info("this is a test");
		// DataBase db = new DataBase();

		try {

			// db.connectDb("144");

			// String sql = "" +
			// "select p.phoneName phoneName,v.VersionNum VersionNum2," +
			// "d.DayDate DayDate,sum(d.UserCnt)  AccUserCount, count(d.NewUserCnt) "
			// +
			// "  AccNewUserCount from " +
			// "ggkingdb.DayCollect d,ggkingdb.Phone p,ggkingdb.Versions v " +
			// " where d.phoneid=p.ID and d.versionid=v.ID   and  " +
			// "d.DayDate between ? and ?"
			// +
			// " group by " +
			// " d.daydate,p.phoneName,v.VersionNum order by d.daydate desc";

			DataBase db = DataBaseFactory.getDataBase();
			String sql = "select * from  test";

			PageBean pageUtils = new PageBean();
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, 1);
			DataBaseSet rs = null;
			// rs = db.doCachedQuery(sql, (Map) null);
			// while (rs.next()) {
			// // log.info(rs.getString("var1") + ":" +
			// rs.getString("var2"));
			// }

			// cc:bb
			// bd:df
			// df:dfs
			// df:df
			db.reConnectDb();
			// "select count(*)from (select top 100 percent * from test order by
			// id) t
			rs = db.doCachedPageQuery(sql, null, 3, 1, pageUtils, "");
			while (rs.next()) {
				log.info("======" + rs.getString("var1") + ":"
						+ rs.getString("var2"));
			}
			rs.close();
			log.info(ObjectUtils.toString(pageUtils));

			RowMapper<List> rowMapper = new RowMapper<List>() {

				public List mapRow(DataBaseSet rs) throws Exception {
					// TODO Auto-generated method stub
					log.info("----" + rs.getString("var1") + ":"
							+ rs.getString("var2"));
					return null;
				}
			};
			db.reConnectDb();
			pageUtils = new PageBean();
			db.doPageQueryObject(sql, null, 3, 1, pageUtils, rowMapper, "10");
			log.info(ObjectUtils.toString(pageUtils));

			db.reConnectDb();
			int rekey = db.executeBindInsertReturnKey(
					"insert into test(var1,var2)values('66','00')", null);
			log.info(rekey);
			// User user = new User();
			// user.setName("孙策");
			// user.setId(123);
			// user.setUpdatetime(new Date());
			// int i = db.excuteDeleteClass(user, "name,id,updatetime");
			// log.info(i);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}

	/**
	 * @param dbUser
	 *            the dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	/**
	 * @return the dbPass
	 */
	public String getDbPass() {
		return dbPass;
	}

	public Connection getConnection() {
		return this.conn;
	}

	/**
	 * @param dbPass
	 *            the dbPass to set
	 */
	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}

	/**
	 * @param dbUrl
	 *            the dbUrl to set
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	@Override
	protected void finalize() throws DbException {
		this.close();

	}

}
