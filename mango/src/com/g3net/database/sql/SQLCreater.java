package com.g3net.database.sql;

/**
 * 基本操作增、删、改、查的SQL语句生成工具类
 * 传入一个数据对象，返回其相应操作的SQL语句
 * 目前对象内部属性只支持String,short,int,long,float,double,java.sql.Date
 * 要求：
 * 1.数据对象的类名要与数据库表名对应，数据对象内部属性要与数据库表列名对应
 * 2.数据对象的构造时需要将内部属性初始化为下列DEFAULT_XXX常量
 * 
 * @author pl_leaf
 */


import java.lang.reflect.Field;
import java.sql.Date;

import cn.net.mpay.bean.Member;

public class SQLCreater {

    public final static String DEFAULT_STRING = null;

    public final static short DEFAULT_SHORT = Short.MIN_VALUE;

    public final static int DEFAULT_INT = Integer.MIN_VALUE;

    public final static long DEFAULT_LONG = Long.MIN_VALUE;

    public final static float DEFAULT_FLOAT = Float.MIN_VALUE;

    public final static double DEFAULT_DOUBLE = Double.MIN_VALUE;

    public final static Date DEFAULT_DATE = null;

    public static String createSQLInsert(Object value)
            throws IllegalArgumentException, IllegalAccessException {
        return createSQLInsert(value, null);
    }

    public static String createSQLDelete(Object value)
            throws IllegalArgumentException, IllegalAccessException {
        return createSQLDelete(value, null);
    }

    public static String createSQLUpdate(Object value, String condition)
            throws IllegalArgumentException, IllegalAccessException {
        return createSQLUpdate(value, null, condition);
    }

    public static String createSQLSelect(Object value)
            throws IllegalArgumentException, IllegalAccessException {
        return createSQLSelect(value, null);
    }
    public static void main(String[] args) {
    	
		try {
			System.out.println(createSQLSelect(Member.class));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

    /**
     * 生成插入语句 将[数据对象]中有数据的属性存入数据库 如[数据对象]中存在不需要存入数据库的属性，将属性名写入[过滤] 格式:
     * |field1|field2|field3|..
     * 
     * @param value
     * @param filter
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String createSQLInsert(Object value, String filter)
            throws IllegalArgumentException, IllegalAccessException {
        if (value==null)
            return "Error: Object is null";
        
        boolean isChangeAccessible;
        Class clazz = value.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer("insert into "
                + clazz.getSimpleName() + "(");
        StringBuffer sbvalue = new StringBuffer(") values (");
        for (int i = 0; i < fields.length; i++) {
            if (isInFilter(filter, fields[i].getName()))
                continue;

            isChangeAccessible = !fields[i].isAccessible();
            fields[i].setAccessible(true);

            if (fields[i].getType().equals(String.class)
                    && fields[i].get(value) != DEFAULT_STRING) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append("'" + fields[i].get(value) + "',");
            } else if (fields[i].getType().equals(int.class)
                    && fields[i].getInt(value) != DEFAULT_INT) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append(fields[i].getInt(value) + ",");
            } else if (fields[i].getType().equals(double.class)
                    && fields[i].getDouble(value) != DEFAULT_DOUBLE) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append(fields[i].getDouble(value) + ",");
            } else if (fields[i].getType().equals(long.class)
                    && fields[i].getLong(value) != DEFAULT_LONG) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append(fields[i].getLong(value) + ",");
            } else if (fields[i].getType().equals(float.class)
                    && fields[i].getFloat(value) != DEFAULT_FLOAT) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append(fields[i].getFloat(value) + ",");
            } else if (fields[i].getType().equals(Date.class)
                    && fields[i].get(value) != DEFAULT_DATE) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append("'" + fields[i].get(value) + "',");
            } else if (fields[i].getType().equals(short.class)
                    && fields[i].getShort(value) != DEFAULT_SHORT) {
                sb.append(fields[i].getName() + ",");
                sbvalue.append(fields[i].getShort(value) + ",");
            }
            if (isChangeAccessible)
                fields[i].setAccessible(false);
        }
        sb.deleteCharAt(sb.length() - 1);
        sbvalue.deleteCharAt(sbvalue.length() - 1);
        return sb.toString() + sbvalue.toString() + ")";
    }

    /**
     * 生成删除语句 如属性全不填则不删除 其余参数请参照createSQLInsert
     * 
     * @param value
     * @param filter
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String createSQLDelete(Object value, String filter)
            throws IllegalArgumentException, IllegalAccessException {
        if (value==null)
            return "Error: Object is null";
        
        boolean isChangeAccessible;
        Class clazz = value.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer("delete from "
                + clazz.getSimpleName() + " where 1=0 or ");

        for (int i = 0; i < fields.length; i++) {
            if (isInFilter(filter, fields[i].getName()))
                continue;

            isChangeAccessible = !fields[i].isAccessible();
            fields[i].setAccessible(true);

            if (fields[i].getType().equals(String.class)
                    && fields[i].get(value) != DEFAULT_STRING) {
                sb.append(fields[i].getName() + "='" + fields[i].get(value)
                        + "' and ");
            } else if (fields[i].getType().equals(int.class)
                    && fields[i].getInt(value) != DEFAULT_INT) {
                sb.append(fields[i].getName() + "=" + fields[i].getInt(value)
                        + " and ");
            } else if (fields[i].getType().equals(double.class)
                    && fields[i].getDouble(value) != DEFAULT_DOUBLE) {
                sb.append(fields[i].getName() + "="
                        + fields[i].getDouble(value) + " and ");
            } else if (fields[i].getType().equals(long.class)
                    && fields[i].getLong(value) != DEFAULT_LONG) {
                sb.append(fields[i].getName() + "=" + fields[i].getLong(value)
                        + " and ");
            } else if (fields[i].getType().equals(float.class)
                    && fields[i].getFloat(value) != DEFAULT_FLOAT) {
                sb.append(fields[i].getName() + "=" + fields[i].getFloat(value)
                        + " and ");
            } else if (fields[i].getType().equals(Date.class)
                    && fields[i].get(value) != DEFAULT_DATE) {
                sb.append(fields[i].getName() + "='" + fields[i].get(value)
                        + "' and ");
            } else if (fields[i].getType().equals(short.class)
                    && fields[i].getShort(value) != DEFAULT_SHORT) {
                sb.append(fields[i].getName() + "=" + fields[i].getShort(value)
                        + " and ");
            }
            if (isChangeAccessible)
                fields[i].setAccessible(false);
        }

        return sb.delete(sb.length() - 4, sb.length() - 1).toString();
    }

    /**
     * 生成更新语句 condition为条件列表，指定[数据对象]中哪些属性是做为条件使用. 格式:
     * |field1|field2|field3|.... 如没有条件则不更新
     * 
     * @param value
     * @param filter
     * @param condition
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String createSQLUpdate(Object value, String filter,
            String condition) throws IllegalArgumentException,
            IllegalAccessException {
        if (value==null)
            return "Error: Object is null";
        
        boolean isChangeAccessible;
        Class clazz = value.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer("update " + clazz.getSimpleName()
                + " set ");
        StringBuffer sbvalue = new StringBuffer(" where 1=0 or ");
        for (int i = 0; i < fields.length; i++) {
            if (isInFilter(filter, fields[i].getName()))
                continue;

            isChangeAccessible = !fields[i].isAccessible();
            fields[i].setAccessible(true);

            if (fields[i].getType().equals(String.class)
                    && fields[i].get(value) != DEFAULT_STRING) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "='"
                            + fields[i].get(value) + "' and ");
                else
                    sb.append(fields[i].getName() + "='" + fields[i].get(value)
                            + "',");
            } else if (fields[i].getType().equals(int.class)
                    && fields[i].getInt(value) != DEFAULT_INT) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "="
                            + fields[i].getInt(value) + " and ");
                else
                    sb.append(fields[i].getName() + "="
                            + fields[i].getInt(value) + ",");
            } else if (fields[i].getType().equals(double.class)
                    && fields[i].getDouble(value) != DEFAULT_DOUBLE) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "="
                            + fields[i].getDouble(value) + " and ");
                else
                    sb.append(fields[i].getName() + "="
                            + fields[i].getDouble(value) + ",");
            } else if (fields[i].getType().equals(long.class)
                    && fields[i].getLong(value) != DEFAULT_LONG) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "="
                            + fields[i].getLong(value) + " and ");
                else
                    sb.append(fields[i].getName() + "="
                            + fields[i].getLong(value) + ",");
            } else if (fields[i].getType().equals(float.class)
                    && fields[i].getFloat(value) != DEFAULT_FLOAT) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "="
                            + fields[i].getFloat(value) + " and ");
                else
                    sb.append(fields[i].getName() + "="
                            + fields[i].getFloat(value) + ",");
            } else if (fields[i].getType().equals(Date.class)
                    && fields[i].get(value) != DEFAULT_DATE) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "='"
                            + fields[i].get(value) + "' and ");
                else
                    sb.append(fields[i].getName() + "=" + fields[i].get(value)
                            + ",");
            } else if (fields[i].getType().equals(short.class)
                    && fields[i].getShort(value) != DEFAULT_SHORT) {
                if (isInFilter(condition, fields[i].getName()))
                    sbvalue.append(fields[i].getName() + "="
                            + fields[i].getShort(value) + " and ");
                else
                    sb.append(fields[i].getName() + "="
                            + fields[i].getShort(value) + ",");
            }
            if (isChangeAccessible)
                fields[i].setAccessible(false);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString() + sbvalue.delete(sbvalue.length()-4, sbvalue.length()-1).toString();
    }

    /**
     * 生成查看语句
     * 
     * @param value
     * @param filter
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String createSQLSelect(Object value, String filter)
            throws IllegalArgumentException, IllegalAccessException {
        if (value==null)
            return "Error: Object is null";
        
        boolean isChangeAccessible;
        Class clazz = value.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer("select * from "
                + clazz.getSimpleName() + " where 1=1");

        for (int i = 0; i < fields.length; i++) {
            if (isInFilter(filter, fields[i].getName()))
                continue;

            isChangeAccessible = !fields[i].isAccessible();
            fields[i].setAccessible(true);

            if (fields[i].getType().equals(String.class)
                    && fields[i].get(value) != DEFAULT_STRING) {
                sb.append(" and " + fields[i].getName() + "='"
                        + fields[i].get(value) + "'");
            } else if (fields[i].getType().equals(int.class)
                    && fields[i].getInt(value) != DEFAULT_INT) {
                sb.append(" and " + fields[i].getName() + "="
                        + fields[i].getInt(value));
            } else if (fields[i].getType().equals(double.class)
                    && fields[i].getDouble(value) != DEFAULT_DOUBLE) {
                sb.append(" and " + fields[i].getName() + "="
                        + fields[i].getDouble(value));
            } else if (fields[i].getType().equals(long.class)
                    && fields[i].getLong(value) != DEFAULT_LONG) {
                sb.append(" and " + fields[i].getName() + "="
                        + fields[i].getLong(value));
            } else if (fields[i].getType().equals(float.class)
                    && fields[i].getFloat(value) != DEFAULT_FLOAT) {
                sb.append(" and " + fields[i].getName() + "="
                        + fields[i].getFloat(value));
            } else if (fields[i].getType().equals(Date.class)
                    && fields[i].get(value) != DEFAULT_DATE) {
                sb.append(" and " + fields[i].getName() + "='"
                        + fields[i].get(value) + "'");
            } else if (fields[i].getType().equals(short.class)
                    && fields[i].getShort(value) != DEFAULT_SHORT) {
                sb.append(" and " + fields[i].getName() + "="
                        + fields[i].getShort(value));
            }
            if (isChangeAccessible)
                fields[i].setAccessible(false);
        }

        return sb.toString();
    }

    /**
     * 如果value在filter中返回真，否则返回假
     * 
     * @param filter
     * @param value
     * @return
     */
    private static boolean isInFilter(String filter, String value) {
        if (filter != null && filter.indexOf("|" + value + "|") != -1)
            return true;
        return false;
    }

}