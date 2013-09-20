package com.g3net.database;

public class MapNest {
	public static int ONE_TO_ONE = 1;
	public static int ONE_TO_MANY = 2;
	public static int MANY_TO_MANY =3;
	
	protected String[] toPros = null;// 对应于javabean的属性名
	protected String nestedBeanName = null;
	protected String prefix = null;
	protected int mapRelation ;
	
	public String[] getToPros() {
		return toPros; 
	}
	public String getNestedBeanName() {
		return nestedBeanName;
	}
	public String getPrefix() {
		return prefix;
	}

	public  int getMapRelation() {
		return mapRelation;
	}
	
	
}
