package com.g3net.database;



public interface RowMapper<T> {
	public T mapRow(DataBaseSet rs) throws Exception;  
}
