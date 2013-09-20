package com.g3net.database;

public class DbException extends Exception {

	

	public DbException() {
		super();
	}

	public DbException(Throwable cause) {
		super(cause);
	}

	public DbException(String msg,Throwable cause) {
		super(msg,cause);
	}
	public DbException(String msg) {
		super(msg);
	}
}