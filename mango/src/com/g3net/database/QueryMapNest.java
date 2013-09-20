package com.g3net.database;

public class QueryMapNest extends MapNest{


	
	public QueryMapNest() {
	}

	public QueryMapNest(String[] toBeanPros, String nestedBeanName, String sqlPrefix) {
		this.toPros = toBeanPros;
		this.nestedBeanName = nestedBeanName;
		this.prefix = sqlPrefix;
		this.mapRelation = QueryMapNest.ONE_TO_ONE;
	}

	public void set(String[] toBeanPros, String nestedBeanName, String sqlPrefix){
		this.toPros=toBeanPros;
		this.nestedBeanName=nestedBeanName;
		this.prefix=sqlPrefix;
		this.mapRelation = QueryMapNest.ONE_TO_ONE;
	}




}
