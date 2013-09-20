package com.g3net.database.dbpool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;
import org.enhydra.jdbc.pool.StandardPoolDataSource;
import org.enhydra.jdbc.pool.StandardXAPoolDataSource;
import org.enhydra.jdbc.standard.StandardConnectionPoolDataSource;
import org.enhydra.jdbc.standard.StandardXADataSource;
import org.objectweb.jotm.Jotm;
import org.objectweb.transaction.jta.TMService;

import com.g3net.database.DbException;
import com.g3net.tool.StringUtils;
import com.g3net.type.TResult2;
import com.jolbox.bonecp.BoneCPDataSource;
import com.jolbox.bonecp.ConnectionHandle;
import com.jolbox.bonecp.hooks.AbstractConnectionHook;
import com.mchange.v2.c3p0.DataSources;

public class DBPoolFactory {
	static class PoolType {
		public static String C3P0 = "c3p0";
		public static String BoneCP = "bonecp";
		public static String jtom = "jtom";
	};

	private static final Logger log = Logger.getLogger(DBPoolFactory.class);
	private static Map<String, DataSource> poollist = new ConcurrentHashMap<String, DataSource>();

	private static Map<String, TResult2<Map<String, DataSource>, TMService>> dispoolist = new ConcurrentHashMap<String, TResult2<Map<String, DataSource>, TMService>>();

	private static DBPoolFactory dbpoolFactory = new DBPoolFactory();

	private DBPoolFactory() {
		try{
			init();
		}catch(Exception e){
			log.error("",e);
		}
	}
	
	public static DBPoolFactory getInstance() {
		return dbpoolFactory;
	}

	/**
	 * 向连接池列表添加一个新的池化（Pooled）数据源
	 * 
	 * @param key
	 * @param configFile
	 *            配置文件名。为了简化参数输入与便于外部维护，可以把配置好的文件传入这里分析
	 */
	public void add(String key, String configFile) {
		// read config from file, and build a dbpool
	}

	public static void setDispoollist() throws DbException{
		Map<String, String[]> transMaps = ReadConfig.getInstance().getTrans();
		// log.debug("---"+C)
		Map<String, Map<String, String>> maps = ReadConfig.getInstance().getProperties();
		Set<String> sets = transMaps.keySet();
		Iterator<String> iter = sets.iterator();
		while (iter.hasNext()) {
			try {
				String transName = iter.next();

				TMService jotm = new Jotm(true, false);

				String[] poolnames = transMaps.get(transName);
				TResult2<Map<String, DataSource>, TMService> t2 = new TResult2<Map<String, DataSource>, TMService>();
				t2.setSecondValue(jotm);
				Map<String, DataSource> dm = new HashMap<String, DataSource>();
				t2.setFirstValue(dm);

				dispoolist.put(transName, t2);

				for (int i = 0; i < poolnames.length; i++) {
					String poolName = poolnames[i];
					Map<String, String> map = maps.get(poolName);

					String driverClassName = map.get("driverClassName");
					String url = map.get("url");
					String user = map.get("username");
					String password = map.get("password");
					String checkoutTimeout = StringUtils.trim(map
							.get("checkoutTimeout"), "5000");// 从连接池取连接的最大等待时间，默认5秒
					// String idleConnectionTestPeriod =
					// map.get("idleConnectionTestPeriod");

					String maxIdleTime = StringUtils.trim(map
							.get("maxIdleTime"), "600000");// 默认空隙10分钟后回收
					String maxPoolSize = StringUtils.trim(map
							.get("maxPoolSize"), "30");// 默认30个
					String minPoolSize = StringUtils.trim(map
							.get("minPoolSize"), "20");// 默认20个

					String initialPoolSize = minPoolSize;// 开始的初始化连接数,默认20个
					StandardXADataSource xads = new StandardXADataSource();

					xads.setUrl(url);
					xads.setDriverName(driverClassName);
					xads.setUser(user);
					xads.setPassword(password);

					StandardXAPoolDataSource spds = new StandardXAPoolDataSource(
							Integer.valueOf(initialPoolSize));
					spds.setMaxSize(Integer.valueOf(maxPoolSize));
					spds.setMinSize(Integer.valueOf(minPoolSize));
					spds.setDeadLockMaxWait(Integer.valueOf(checkoutTimeout));
					spds.setLifeTime(Integer.valueOf(maxIdleTime));

					spds.setUser(user);
					spds.setPassword(password);
					/**
					 * sleepTime是对Connection idle检测线程PoolKeeper的检测时间间隔设置。
					 * PoolKeeper会定时监测是否存在超过lifeTime的connection然后释放掉这些connection
					 **/
					spds.setSleepTime(360 * 1000);// PoolKeeper检测时间间隔,360秒
					spds.setDeadLockRetryWait(3 * 1000);// 重试的时间间隔,3秒

					spds.setTransactionManager(jotm.getTransactionManager());
					spds.setDataSource(xads);

					dm.put(poolName, spds);
				}

			} catch (Exception e) {
				throw new DbException(e);
			}
		}
	}

	public static void setPoollist() throws DbException{

		try {

			Map<String, Map<String, String>> maps = ReadConfig.getInstance().getProperties();

			Set<String> sets = maps.keySet();
			Iterator<String> iter = sets.iterator();
			while (iter.hasNext()) {
				try {

					String poolName = iter.next();
					Map<String, String> map = maps.get(poolName);

					String driverClassName = map.get("driverClassName");
					String url = map.get("url");
					String user = map.get("username");
					String password = map.get("password");

					String maxStatements = StringUtils.trim(map
							.get("maxStatements"), "30");
					String checkoutTimeout = StringUtils.trim(map
							.get("checkoutTimeout"), "60000");// 60000毫秒
					String idleConnectionTestPeriod = StringUtils.trim(map
							.get("idleConnectionTestPeriod"), "40");// 40秒
					String type = StringUtils.trim(map.get("type"), PoolType.C3P0);
					String maxIdleTime = StringUtils.trim(map
							.get("maxIdleTime"), "600");// 以秒为单位，默认空隙600秒后回收
					String maxPoolSize = StringUtils.trim(map
							.get("maxPoolSize"), "30");// 默认30个
					String minPoolSize = StringUtils.trim(map
							.get("minPoolSize"), "10");// 默认20个
					// String maxStatements =map.get("maxStatements");
					String onlyForDis = StringUtils.trim(map.get("onlyForDis"),
							"").toLowerCase();

					Class.forName(driverClassName);

					if (onlyForDis.equals("true")) {
						continue;
					}
					DataSource ds = null;
					if (type.equals(PoolType.C3P0)) {

						DataSource ds_unpooled = DataSources
								.unpooledDataSource(url, user, password);

						Map<String, String> config = new HashMap<String, String>();
						config.put("checkoutTimeout", checkoutTimeout);// 1分钟
						config.put("maxPoolSize", maxPoolSize);
						config.put("minPoolSize", minPoolSize);
						config.put("maxStatements", maxStatements);
						config.put("maxIdleTime", maxIdleTime);
						config.put("initialPoolSize", minPoolSize);
						config.put("idleConnectionTestPeriod",
								idleConnectionTestPeriod);
						DataSource ds_pooled = DataSources.pooledDataSource(
								ds_unpooled, config);
						ds = ds_pooled;
					} else if (type.equals(PoolType.BoneCP)) {
						// load the DB driver
						BoneCPDataSource dataSource = new BoneCPDataSource();
						// dataSource.setLogStatementsEnabled(false);
						dataSource.setJdbcUrl(url); // set the JDBC url
						dataSource.setUsername(user); // set the username
						dataSource.setPassword(password); // set the password
						// dataSource.setIdleMaxAge((long)maxage);//
						int max = Integer.valueOf(maxPoolSize);
						int min = Integer.valueOf(minPoolSize);
						int idle= Integer.valueOf(maxIdleTime) / 60;
						// dataSource.setReleaseHelperThreads(0);
						// dataSource.setAcquireRetryDelay(acquireRetryDelay);
						dataSource.setPartitionCount(1);
						dataSource.setAcquireIncrement(1);
						dataSource.setAcquireRetryDelay(checkoutTimeout);
						dataSource.setMaxConnectionsPerPartition(max);
						dataSource.setMinConnectionsPerPartition(min);
						dataSource.setLogStatementsEnabled(false);
						int test=Integer.valueOf(idleConnectionTestPeriod)/60;
						dataSource.setIdleConnectionTestPeriod((long)test);
						dataSource.setIdleMaxAge((long)idle);
						int statment=Integer.valueOf(maxStatements);
						dataSource.setPreparedStatementCacheSize(statment);
						dataSource.setStatementsCachedPerConnection(statment);
						//dataSource.setAcquireRetryDelay(1);
						ds = dataSource;

					}
					// ds.setXXXX(...); // (other config options here)

					if (poollist.containsKey(poolName)) {
						if (type == PoolType.BoneCP) {
							BoneCPDataSource old_pooled = (BoneCPDataSource) poollist
									.get(poolName);
							old_pooled.close();

						} else if (type == PoolType.C3P0) {
							DataSource old_pooled = poollist.get(poolName);
							DataSources.destroy(old_pooled);
						}
						poollist.remove(poolName);
					}
					poollist.put(poolName, ds);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					throw ex;
				}
			}
		} catch (Exception e) {
			log.error("", e);
			new DbException("add database pool error!", e);
		}

	}

	/**
	 * 向连接池列表添加一个新的池化（Pooled）数据源
	 * 
	 * @param key
	 * @param driver
	 *            数据连接驱动，如com.mysql.jdbc.Driver
	 * @param url
	 *            连接串， 如jdbc:mysql://localhost/samland
	 * @param username
	 *            数据库连接用户名，如samland
	 * @param password
	 *            数据库连接用户密码
	 * @throws Exception
	 */
	public static void init() throws DbException{

		try {

			DBPoolFactory.setPoollist();
			DBPoolFactory.setDispoollist();

		} catch (Exception e) {
			log.error("", e);
			new DbException("add database pool error!", e);
		}
	}

	

	/**
	 * 根据key字符串获取连接池
	 * 
	 * @param key
	 * @return DBPool 连接池
	 * @throws Exception
	 */
	public static DataSource getDBPool(String key) throws DbException {
		return getDBPool(key, null);
	}

	/**
	 * 
	 * @param key
	 *            连接池名
	 * @param pros
	 *            返回的属性
	 * @return
	 * @throws DbException
	 */
	public static DataSource getDBPool(String key, Map<String, String> pros)
			throws DbException {
		try {
			Map<String, Map<String, String>> maps = ReadConfig.getInstance().getProperties();
			if (pros != null) {
				pros.putAll(maps.get(key));
			}

			if (key == null)
				throw new DbException("DBPool 'key' CANNOT be null");
			DataSource ds = (DataSource) poollist.get(key);

			return ds;
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public TResult2<Map<String, DataSource>, TMService> get(String transName) {
		TResult2<Map<String, DataSource>, TMService> t2 = dispoolist
				.get(transName);
		return t2;
	}

	public DataSource getDBPool(String transName, String poolName,
			Map<String, String> pros) throws DbException {
		try {
			Map<String, Map<String, String>> maps = ReadConfig.getInstance().getProperties();
			if (pros != null) {
				pros.putAll(maps.get(poolName));
			}

			if (poolName == null)
				throw new DbException("DBPool 'poolName' CANNOT be null");
			TResult2<Map<String, DataSource>, TMService> t2 = dispoolist
					.get(transName);
			Map<String, DataSource> poollist = t2.getFirstValue();
			DataSource ds = (DataSource) poollist.get(poolName);

			return ds;
		} catch (Exception e) {
			throw new DbException(e);
		}
	}

	public static void clearAll() throws DbException {
		try {
			// 清空常规连接池
			Set<String> sets = poollist.keySet();
			Iterator<String> iter = sets.iterator();
			while (iter.hasNext()) {
				String poolName = iter.next();
				DataSource ds =poollist.get(poolName);
				if(ds instanceof BoneCPDataSource){
					BoneCPDataSource old_pooled = (BoneCPDataSource) poollist
							.get(poolName);
					old_pooled.close();
				}else {
					DataSources.destroy(ds);
				}

			}
			poollist.clear();
			
			// 清除分布式连接池
			Set<String> sets2 = dispoolist.keySet();
			Iterator<String> iter2 = sets2.iterator();
			while (iter2.hasNext()) {
				String transName = iter2.next();
				TResult2<Map<String, DataSource>, TMService> t2 = dispoolist
						.get(transName);
				Map<String, DataSource> poollist = t2.getFirstValue();
				TMService jotm = t2.getSecondValue();
				UserTransaction ut = jotm.getUserTransaction();
				// log.info(ut.getStatus());
				if (ut.getStatus() == Status.STATUS_ACTIVE)// 只开始了事务，但未回滚或提交。
				{
					jotm.getUserTransaction().rollback();
				}
				jotm.stop();
				Set<String> poolKeys = poollist.keySet();
				Iterator<String> keyIter = poolKeys.iterator();
				while (keyIter.hasNext()) {
					String poolName = keyIter.next();
					DataSource ds = (DataSource) poollist.get(poolName);
					StandardXAPoolDataSource old_pooled = (StandardXAPoolDataSource) poollist
							.get(poolName);
					old_pooled.stopPool();
				}
				poollist.clear();

			}
			dispoolist.clear();

		} catch (Exception e) {
			throw new DbException("clear database pool error!", e);
		}
	}



}
