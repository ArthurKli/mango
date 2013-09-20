package com.g3net.database;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.objectweb.transaction.jta.TMService;

import com.g3net.database.dbpool.DBPoolFactory;
import com.g3net.type.TResult2;

/**
 * <code>XaDataBase</code>支持分布式事务，底层封装了jotm分布式事务管理器。可以实现对不同数据库的多个操作的回滚与提交。
 * 更强大的用法可参见 {@link DataBase}类。 分布式事务操作简单用法：
 * <p>
 * <blockquote>
 * 
 * <pre>
 * XaDataBase dd=new XaDataBase("分布式数据库群名称，对应于dbpool.xml文件中的dis-transaction标签");
 * 		
 * 		try {
 * 			dd.begin();//开始事务
 * 			DataBase db=dd.getDataBase("tt1");//获取数据库tt1的操作对象
 * 			System.out.println(db.getAutoCommit());
 * 			Map<Integer, Object> vParameters=new HashMap<Integer, Object>();
 * 			vParameters.put(1, 5);
 * 			db.executeBindUpdate("update testdata set foo = ? where id=1", vParameters);//执行更新操作
 * 			
 * 			db=dd.getDataBase("tt1");//由于上一步操作已经将connection对象返回给连接池，所以需要重新获取,或者调用该对象的reConnectDb()方法
 * 			vParameters.put(1, 23);
 * 			db.executeBindUpdate("update testdata set foo = ? where id=1", vParameters);
 * 			
 * 			db=dd.getDataBase("tt2");//获取数据库tt2的操作对象
 * 			vParameters=new HashMap<Integer, Object>();
 * 			vParameters.put(1, 2);
 * 			db.executeBindUpdate("update testdata set foo = ? where id=1", vParameters);
 * 			
 * 			dd.commit();//提交所有的操作。如果不提交，则之前所做的操作均不会更新数据库
 * //			dd.rollback();//回滚所有操作
 * 		} catch (Exception e) {
 * 			// TODO Auto-generated catch block
 * 			e.printStackTrace();
 * 		}finally{
 * 			DBPoolFactory.clearAll();//记住在释放连接池资源
 * }
 * 
 * <pre>
 * </p>
 * 
 * @author sunchaojin
 * 
 */
public class XaDataBase {

	private TMService jotm = null;
	private Map<String, DataSource> dataSources = null;

	public XaDataBase(String disTransName) {
		DBPoolFactory dbf = DBPoolFactory.getInstance();
		TResult2<Map<String, DataSource>, TMService> t2 = dbf.get(disTransName);
		jotm = t2.getSecondValue();
		dataSources = t2.getFirstValue();
	}

	public void begin() throws DbException {
		try {
			jotm.getUserTransaction().begin();
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public DataBase getDataBase(String poolName) throws DbException {
		try {
			DataBase db = new DataBase();
			DataSource datasource = (DataSource) dataSources.get(poolName);

			db.connectDb(datasource, true);
			return db;
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public void rollback() throws DbException {
		try {
			jotm.getUserTransaction().rollback();
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public void commit() throws DbException {
		try {
			jotm.getUserTransaction().commit();
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
