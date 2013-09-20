package com.g3net.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存放一到多关联关系的类
 * @author sunchaojin
 *
 */
public class QueryMapNestOne2Many extends MapNest {

	private Class classType;

	private String nestedBeanKey = "";// 子关联类主键属性，如果为复合主键，以","隔开

	// 存放父亲主键(复合主键以逗号隔开)对应的返回classType类的映射列表
	private Map<String, List> result = new HashMap<String, List>();

	public QueryMapNestOne2Many() {

	}

	public String getNestedBeanKey() {
		return nestedBeanKey;
	}

	/**
	 * 设置关联属性
	 * 
	 * @param nestedBeanClassType
	 *            对应子关联类的Class
	 * @param nestedBeanName
	 *            对应子关联类属性名
	 * @param nestedBeanKey
	 *            子关联类的主键属性，如果为复合主键，以","隔开
	 * @param sqlPrefix
	 *            子关联类对应sql语句的前缀
	 * @param toBeanPros
	 *            子关联类所映射的属性名，如果为null，子关联类的所有属性全部映射
	 */
	public void set(Class nestedBeanClassType, String nestedBeanName,
			String nestedBeanKey, String sqlPrefix, String[] toBeanPros) {

		this.toPros = toBeanPros;
		this.classType = nestedBeanClassType;
		this.prefix = sqlPrefix;
		this.nestedBeanKey = nestedBeanKey;
		this.nestedBeanName = nestedBeanName;
		// this.key=key;
		this.mapRelation = MapNest.ONE_TO_MANY;
	}

	/**
	 * 设置关联属性
	 * 
	 * @param nestedBeanClassType
	 *            对应子关联类的Class
	 * @param nestedBeanName
	 *            对应子关联类属性名
	 * @param nestedBeanKey
	 *            子关联类的主键属性，如果为复合主键，以","隔开
	 * @param sqlPrefix
	 *            子关联类对应sql语句的前缀
	 * @param toBeanPros
	 *            子关联类所映射的属性名，如果为null，子关联类的所有属性全部映射
	 */
	public QueryMapNestOne2Many(Class nestedBeanClassType,
			String nestedBeanName, String nestedBeanKey, String sqlPrefix,
			String[] toBeanPros) {
		// QueryMapNest(String[] toPros, String nestedBeanName, String prefix)
		this.toPros = toBeanPros;
		this.classType = nestedBeanClassType;
		this.prefix = sqlPrefix;
		this.nestedBeanKey = nestedBeanKey;
		this.nestedBeanName = nestedBeanName;
		this.mapRelation = MapNest.ONE_TO_MANY;

	}

	public Map<String, List> getResult() {
		return result;
	}

	public Class getClassType() {
		return classType;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryMapNestOne2Many qs = new QueryMapNestOne2Many();
		Class<String> s = qs.getClassType();
	}

}
