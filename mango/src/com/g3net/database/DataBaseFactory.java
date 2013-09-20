package com.g3net.database;

public class DataBaseFactory {


	
	/**
	 * 用于从连接池构造DataBase对象，连接池配置文件在src目录下的dbpool.xml里
	 * @return
	 * @throws DbException
	 */
	public static DataBase getDataBase() throws DbException {
		DataBase db = new DataBase();
		try {
			db.connectDb("tt1");
//			 db.connectDb("gameuser", "jb98",
//		 "jdbc:mysql://localhost:10001/versiondb",
//			 "com.mysql.jdbc.Driver");
		} catch (DbException e) {
			throw new DbException("can't connect database!", e);
		}
		return db;

	}
	
	
	/**
	 * 
	 * @param dataSourceName 连接池的名字
	 * @return
	 * @throws DbException
	 */
	public static DataBase getDataBase(String dataSourceName) throws DbException {
		DataBase db = new DataBase();
		try {
			db.connectDb(dataSourceName);
//			 db.connectDb("gameuser", "jb98",
//		 "jdbc:mysql://localhost:10001/versiondb",
//			 "com.mysql.jdbc.Driver");
		} catch (DbException e) {
			throw new DbException("can't connect database!", e);
		}
		return db;

	}
	/**
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DataBase db=DataBaseFactory.getDataBase();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
