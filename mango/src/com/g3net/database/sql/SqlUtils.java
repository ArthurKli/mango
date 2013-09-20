package com.g3net.database.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.g3net.database.DataBaseSet;
import com.g3net.database.DbException;
import com.g3net.tool.ArrayUtils;
import com.g3net.tool.CTime;
import com.g3net.tool.FileUtils;
import com.g3net.tool.StringUtils;
import com.sun.rowset.CachedRowSetImpl;

class Column {
	public String getTable_cat() {
		return table_cat;
	}

	public void setTable_cat(String table_cat) {
		this.table_cat = table_cat;
	}

	public String getTable_schem() {
		return table_schem;
	}

	public void setTable_schem(String table_schem) {
		this.table_schem = table_schem;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public int getData_type() {
		return data_type;
	}

	public void setData_type(int data_type) {
		this.data_type = data_type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public int getColumn_size() {
		return column_size;
	}

	public void setColumn_size(int column_size) {
		this.column_size = column_size;
	}

	public int getNum_prec_radix() {
		return num_prec_radix;
	}

	public void setNum_prec_radix(int num_prec_radix) {
		this.num_prec_radix = num_prec_radix;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIs_nullable() {
		return is_nullable;
	}

	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}

	public String getIs_autoincrement() {
		return is_autoincrement;
	}

	public void setIs_autoincrement(String is_autoincrement) {
		this.is_autoincrement = is_autoincrement;
	}

	private String table_cat = "";
	private String table_schem = "";
	private String table_name = "";
	private String column_name = "";
	private int data_type;// SQL type from java.sql.Types
	private String type_name;
	private int column_size;
	private int num_prec_radix;
	private String remarks;
	private String is_nullable;// "YES","NO" or ""
	private String is_autoincrement;// "YES","NO" or ""
}

public class SqlUtils {
	private static Logger log = Logger.getLogger(SqlUtils.class);
	public static Set<Class> simpleType = new HashSet<Class>();
	public static Map<Integer, Class> sql2javaType = new HashMap<Integer, Class>();

	public static Map<Class, Integer> javaType2sql = new HashMap<Class, Integer>();

	static {
		simpleType.add(boolean.class);
		simpleType.add(Boolean.class);
		simpleType.add(String.class);
		simpleType.add(char.class);
		simpleType.add(Character.class);
		simpleType.add(byte.class);
		simpleType.add(Byte.class);
		simpleType.add(Integer.class);
		simpleType.add(int.class);
		simpleType.add(Long.class);
		simpleType.add(long.class);
		simpleType.add(Short.class);
		simpleType.add(short.class);
		simpleType.add(Float.class);
		simpleType.add(float.class);
		simpleType.add(Double.class);
		simpleType.add(double.class);
		simpleType.add(java.util.Date.class);

		// ////////////////
		sql2javaType.put(java.sql.Types.BIGINT, Long.class);
		sql2javaType.put(java.sql.Types.INTEGER, Integer.class);
		sql2javaType.put(java.sql.Types.ARRAY, java.sql.Array.class);
		sql2javaType.put(java.sql.Types.BLOB, java.sql.Blob.class);
		sql2javaType.put(java.sql.Types.CLOB, java.sql.Clob.class);
		sql2javaType.put(java.sql.Types.BOOLEAN, Boolean.class);
		sql2javaType.put(java.sql.Types.CHAR, String.class);
		sql2javaType.put(java.sql.Types.DATE, java.util.Date.class);
		sql2javaType.put(java.sql.Types.DECIMAL, Double.class);
		sql2javaType.put(java.sql.Types.SMALLINT, Integer.class);
		sql2javaType.put(java.sql.Types.TINYINT, Integer.class);
		sql2javaType.put(java.sql.Types.TIME, java.util.Date.class);
		sql2javaType.put(java.sql.Types.TIMESTAMP, java.util.Date.class);
		sql2javaType.put(java.sql.Types.VARCHAR, String.class);
		sql2javaType.put(java.sql.Types.NUMERIC, Double.class);

		sql2javaType.put(java.sql.Types.STRUCT, java.sql.Struct.class);
		sql2javaType.put(java.sql.Types.REAL, Float.class);
		sql2javaType.put(java.sql.Types.LONGVARCHAR, String.class);
		sql2javaType.put(java.sql.Types.FLOAT, Float.class);
		sql2javaType.put(java.sql.Types.DOUBLE, Double.class);

		sql2javaType.put(java.sql.Types.BINARY, byte[].class);
		sql2javaType.put(java.sql.Types.BIT, Boolean.class);
		sql2javaType.put(java.sql.Types.REF, Object.class);
		sql2javaType.put(java.sql.Types.VARBINARY, byte[].class);
		sql2javaType.put(java.sql.Types.LONGVARBINARY, byte[].class);

		// //////
		javaType2sql.put(Long.class, java.sql.Types.BIGINT);
		javaType2sql.put(long.class, java.sql.Types.BIGINT);
		javaType2sql.put(Integer.class, java.sql.Types.INTEGER);
		javaType2sql.put(int.class, java.sql.Types.INTEGER);
		javaType2sql.put(java.sql.Array.class, java.sql.Types.ARRAY);
		javaType2sql.put(java.sql.Blob.class, java.sql.Types.BLOB);
		javaType2sql.put(java.sql.Clob.class, java.sql.Types.CLOB);
		javaType2sql.put(Boolean.class, java.sql.Types.BOOLEAN);
		javaType2sql.put(boolean.class, java.sql.Types.BOOLEAN);
		javaType2sql.put(java.util.Date.class, java.sql.Types.TIMESTAMP);
		javaType2sql.put(Double.class, java.sql.Types.DOUBLE);
		javaType2sql.put(double.class, java.sql.Types.DOUBLE);
		javaType2sql.put(String.class, java.sql.Types.VARCHAR);
		javaType2sql.put(java.sql.Struct.class, java.sql.Types.STRUCT);
		javaType2sql.put(Float.class, java.sql.Types.FLOAT);
		javaType2sql.put(float.class, java.sql.Types.FLOAT);
		javaType2sql.put(byte[].class, java.sql.Types.BINARY);
		javaType2sql.put((Class) DataBaseSet.class, java.sql.Types.REF);
		javaType2sql.put(java.sql.ResultSet.class, java.sql.Types.REF);
		// javaType2sql.put(Object.class ,java.sql.Types.REF );

	}

	public static java.sql.Date DateWholeToSqlDate(String wholeDate) {
		return new java.sql.Date(CTime.parseDayDate(wholeDate).getTime());
	}

	public static java.util.Date sqlTimestampTojavaDate(java.sql.Timestamp value) {
		java.sql.Timestamp time = value;
		Date dateTime = new Date();
		dateTime.setTime(time.getTime());
		return dateTime;

	}

	public static java.sql.Date dateYMDToSqlDate(String wholeDate) {
		return new java.sql.Date(CTime.parseDayDate(wholeDate).getTime());
	}

	public static boolean checkedSimpleType(Class t) {
		if (simpleType.contains(t)) {
			return true;
		}
		return false;
	}

	private static String exportJavaBean(String tableName, Map map,
			String toPackage) {
		Set set = map.keySet();
		Iterator i = set.iterator();
		StringBuilder sb = new StringBuilder();
		StringBuilder sm = new StringBuilder();
		if (toPackage != null && !toPackage.trim().equals("")) {
			sb.append("package " + toPackage + ";\n");
		}
		sb.append("import java.util.*;\n\n");
		sb.append("public class " + (tableName.charAt(0) + "").toUpperCase()
				+ tableName.substring(1) + "{\n\n");
		String toStringMethod = "\n\tpublic String toString(){\n";
		String tmpStr = "\"{\"";
		int n = 0;
		while (i.hasNext()) {
			String name = (String) i.next();
			Column co = (Column) map.get(name);
			Integer type = co.getData_type();
			if (name.trim().equals("class")) {
				name = "class_";
			}
			if (n == 0) {
				tmpStr = tmpStr + "+\"" + name + "=\"+this." + name + "";
			} else {
				tmpStr = tmpStr + "+\";" + name + "=\"+this." + name + "";
			}
			n++;
			Class typeClass = (Class) SqlUtils.sql2javaType.get(type);
			// log.info("sssss "+co.getType_name());
			String typeName = typeClass.getSimpleName();
			if (SqlUtils.checkedSimpleType(typeClass)) {
				typeName = typeClass.getSimpleName();
			} else {
				typeName = typeClass.getName();
			}

			sb
					.append("\t" + "private " + typeName + " " + name
							+ ";//remark:" + co.getRemarks() + ";length:"
							+ co.getColumn_size() + "\n");

			sm.append("\t" + "public void set"
					+ (name.charAt(0) + "").toUpperCase() + name.substring(1)
					+ "(" + typeName + " " + name + "){\n");
			sm.append("\t\tthis." + name + " = " + name + ";\n\t}");
			sm.append("\n\t" + "public " + typeName + " get"
					+ (name.charAt(0) + "").toUpperCase() + name.substring(1)
					+ "(){\n");
			sm.append("\t\treturn " + name + ";\n\t}\n");
		}
		tmpStr = tmpStr + "+\"}\"";
		//toStringMethod = toStringMethod + "\t\treturn " + tmpStr + ";\n\t}\n";
		toStringMethod="";
		sb.append("\n" + sm);
		sb.append(toStringMethod);
		sb.append("\n}");
		return sb.toString();
	}

	/**
	 * 从某个数据库中导出javabean类，每个表的字段对应javabean相应的属性<br/>
	 * 例子如下：
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * exportTables(&quot;com.mysql.jdbc.Driver&quot;, &quot;jdbc:mysql://localhost:4001/gglook&quot;,
	 * 		&quot;gglook&quot;, &quot;jb98&quot;, &quot;gglook&quot;, &quot;e:/okok2&quot;, &quot;com.model&quot;, &quot;utf-8&quot;);
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * @param driver
	 *            数据库Driver
	 * @param connStr
	 *            连接字符串
	 * @param user
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param schema
	 *            数据库名
	 * @param toFolder
	 *            导出的javabean类存放的目录
	 * @param toPackage
	 *            导出的javabean类所属的包名
	 * @param remarkEncoding
	 *            导出的javabean类文件(.java)编码
	 */
	public static void exportTables(String driver, String connStr, String user,
			String password, String schema, String toFolder, String toPackage,
			String remarkEncoding) {

		Connection conn = null;
		try {
			// log.info("aaaaaaaa");
			// Class.forName("com.mysql.jdbc.Driver");
			// String sConnStr =
			// "jdbc:mysql://localhost:3306/gglook";
			// conn =
			// DriverManager.getConnection(connStr,user,password);

			Class.forName(driver);
			String sConnStr = connStr;
			conn = DriverManager.getConnection(connStr, user, password);
			DatabaseMetaData dd = conn.getMetaData();
			ResultSet rs = dd.getTables(null, schema, null,
					new String[] { "TABLE" });
			ArrayList tablelist = new ArrayList();
			while (rs.next()) {
				// log.info(rs.getString("TABLE_NAME"));
				tablelist.add(rs.getString("TABLE_NAME"));
			}
			// log.info("cccccccccc");
			rs.close();
			for (int i = 0; i < tablelist.size(); i++) {

				rs = dd.getColumns(null, null, (String) tablelist.get(i), null);
				String tableName = (String) tablelist.get(i);
				tableName = StringUtils.capitalize(tableName);
				// log.info("test1");
				Map map = new HashMap();

				while (rs.next()) {
					Column co = new Column();
					co.setColumn_name(rs.getString("COLUMN_NAME"));
					co.setColumn_size(rs.getInt("COLUMN_SIZE"));
					co.setData_type(rs.getInt("DATA_TYPE"));
					// co.setIs_autoincrement(rs.getString("IS_AUTOINCREMENT"));
					// co.setIs_nullable(rs.getString("IS_NULLABL"));
					co.setRemarks(new String(rs.getBytes("REMARKS"),
							remarkEncoding));
					co.setTable_cat(rs.getString("TABLE_CAT"));
					co.setTable_name(rs.getString("TABLE_NAME"));
					co.setTable_schem(rs.getString("TABLE_SCHEM"));
					co.setType_name(rs.getString("TYPE_NAME"));
					// remarkEncoding
					// log.info("table:"+co.getTable_name()+" colum:"+co.getColumn_name()+"remark "+new
					// String(rs.getBytes("REMARKS"),"utf-8"));
					// log.info(rs.getString("COLUMN_NAME")
					// +":"+rs.getString("TYPE_NAME")+":"
					// +rs.getString("DATA_TYPE")+"----"+SqlUtils.sql2javaType.get(rs.getInt("DATA_TYPE")));

					map.put(rs.getString("COLUMN_NAME"), co);

					// log.info(exportJavaBean((String)tablelist.get(i),map,""));

					FileUtils.write(toFolder + "/" + tableName + ".java",
							exportJavaBean((String) tablelist.get(i), map,
									toPackage), "utf-8");
				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		 exportTables("com.mysql.jdbc.Driver",
		 "jdbc:mysql://localhost:3306/versiondb", "root",
		 "root", "versiondb", "e:/okok3", "com.model", "utf-8");
		// Map map = new HashMap();
		// Platform p = new Platform();

		// String sql = SqlUtils.generateUpdateSql(Platform.class, p,
		// "id,platform" ,map);
		// log.info(sql);
//		List list = new ArrayList();
//		String sql = "select * from news where id=? and id2=? and id3=? and time1=?";
//		list.add(123);
//		list.add(12.345);
//		list.add("2222");
//		list.add(new Date());
//		list.add(new java.sql.Timestamp(new Date().getTime()));
//		log.info(SqlUtils.generateNotParmatersSql(sql, list));

	}

	public static Object getValueFromResult(Class t, String prefix,
			String name, ResultSet rs, Map<String, String> map) {

		Object value = null;
		// log.info("class="+t+" name="+name);
		try {

			if (map != null) {
				String mapName = map.get(name);
				if (mapName != null) {
					name = mapName;
				}
			}
			name = prefix + name;
			if (rs.getObject(name) == null)// 检测是否为空值,如果为空，返回控制
				return null;

			if (t == String.class) {

				value = rs.getString(name);

			} else if (t == Integer.class || t == int.class) {

				value = rs.getInt(name);

			} else if (t == boolean.class || t == Boolean.class) {

				value = rs.getBoolean(name);

			} else if (t == Long.class || t == long.class) {
				value = rs.getLong(name);

			} else if (t == Short.class || t == short.class) {
				value = rs.getShort(name);

			} else if (t == Float.class || t == float.class) {
				value = rs.getFloat(name);

			} else if (t == Double.class || t == double.class) {
				value = rs.getDouble(name);

			} else if (t == java.util.Date.class) {
				value = rs.getTimestamp(name);
				// value = (Date) value;
				value = SqlUtils
						.sqlTimestampTojavaDate((java.sql.Timestamp) value);
			} else if (t == java.sql.Date.class) {
				value = rs.getDate(name);
			} else if (t == java.sql.Timestamp.class) {
				value = rs.getTimestamp(name);
			} else if (t == Byte.class || t == byte.class) {
				value = rs.getByte(name);
			} else if (t == char.class || t == Character.class) {
				value = rs.getString(name);
			}

			else {

				log.debug("class[" + t.getName()
						+ "] can't be handled and return null!");
				return null;
				// value=rs.getObject(name);
			}
		} catch (Exception ex) {
			log.debug("'" + name + "' is not  set , [" + t + "] property!");
		}
		return value;
	}

	public static void getValueFromCallableStatement(CallableStatement rs,
			Map<Integer, Object> params, Map<Integer, Object> returnKeyValues)
			throws Exception {

		Object value = null;
		// log.info("class="+t+" name="+name);
		try {

			Set<Integer> keys = params.keySet();
			for (Integer key : keys) {
				// Object val=cstmt.getObject(key);
				Object val = params.get(key);
				if (!(val instanceof Class)) {
					val = val.getClass();
				}
				Class t = (Class) val;
				if (t == String.class) {

					value = rs.getString(key);

				} else if (t == Integer.class || t == int.class) {

					value = rs.getInt(key);

				} else if (t == boolean.class || t == Boolean.class) {

					value = rs.getBoolean(key);

				} else if (t == Long.class || t == long.class) {
					value = rs.getLong(key);

				} else if (t == Short.class || t == short.class) {
					value = rs.getShort(key);

				} else if (t == Float.class || t == float.class) {
					value = rs.getFloat(key);

				} else if (t == Double.class || t == double.class) {
					value = rs.getDouble(key);

				} else if (t == java.util.Date.class) {
					value = rs.getTimestamp(key);
					// value = (Date) value;
					value = SqlUtils
							.sqlTimestampTojavaDate((java.sql.Timestamp) value);
				} else if (t == java.sql.Date.class) {
					value = rs.getDate(key);
				} else if (t == java.sql.Timestamp.class) {
					value = rs.getTimestamp(key);
				} else if (t == Byte.class || t == byte.class) {
					value = rs.getByte(key);
				} else if (t == char.class || t == Character.class) {
					value = rs.getString(key);
				} else if (t == DataBaseSet.class) {
					value = rs.getObject(key);
					ResultSet rss = null;
					CachedRowSet crs = null;
					try {
						rss = (ResultSet) value;
						crs = new CachedRowSetImpl();
						rss.beforeFirst();
						crs.populate(rss);
					} finally {
						if (rss != null) {
							rss.close();
						}
					}
					Object temp = new DataBaseSet(crs);
				} else {

					value = rs.getObject(key);
					if (value instanceof ResultSet) {
						value = rs.getObject(key);
						ResultSet rss = null;
						CachedRowSet crs = null;
						try {
							rss = (ResultSet) value;
							crs = new CachedRowSetImpl();
							rss.beforeFirst();
							crs.populate(rss);
						} finally {
							if (rss != null) {
								rss.close();
							}
						}
						Object temp = new DataBaseSet(crs);
						value = temp;
					} else {
						throw new Exception("position[" + key + "] class["
								+ t.getName() + "] can't be decided!");
					}

				}
				returnKeyValues.put(key, value);
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}

	public static String generateDeleteSql(Object deleteObject, String deleteProperteis,
			Map<Integer, Object> returnvParameters, Map<String, String> exmap)
			throws Exception {

		String sql = "";
		String tableName = deleteObject.getClass().getSimpleName();
		sql = "delete from " + tableName.toLowerCase() + " ";

		String[] keys = deleteProperteis.split(",|;");
		try {
			
			int index=1;

			String where = " where ";

			for (int n = 0; n < keys.length; n++) {
				Object value = PropertyUtils.getProperty(deleteObject, keys[n]);
				where = where + keys[n] + "=?";
				if (n < keys.length - 1) {
					where = where + " and ";
				}
				returnvParameters.put(index++, value);
			}
			sql = sql + where;
			log.debug("generated sql:" + sql);
			return sql;
		} catch (Exception e) {
			throw e;
		}

	}
	/**
	 * 
	 * @param properties
	 *            需更新的属性，如果为空，表明更新所有主键属性以外的属性
	 * @param updateObject
	 *            需更新的对象
	 * @param beankey
	 *            updateObject对象里为主键属性名，复合主键以","隔开，主键属性用于唯一标示一个对象，通过它生成where语句
	 * @param returnvParameters
	 *            生成的参数，每个参数对应于生成语句的一个"?"字符
	 * @param exmap
	 *            名字映射，可能数据库里的字段映射到updateObject对象的属性时，
	 *            属性名可能为java语言的关键字，examp存放关键字的映射，通过此 映射可以转换成其他的名字
	 * @return
	 * @throws Exception
	 */
	public static String generateUpdateSql(String[] properties,
			Object updateObject, String beankey,
			Map<Integer, Object> returnvParameters, Map<String, String> exmap)
			throws Exception {

		String sql = "";
		String tableName = updateObject.getClass().getSimpleName();
		sql = "update " + tableName.toLowerCase() + " ";
		String colPart = "set ";
		String[] keys = beankey.split(",|;");
		try {

			Map<?, ?> map = PropertyUtils.describe(updateObject);
			Set<?> set = map.keySet();
			Iterator<?> i = set.iterator();
			int index = 1;
			while (i.hasNext()) {

				String name = (String) i.next();

				if (name.equals("class")) {
					continue;
				}

				if (properties != null
						&& !ArrayUtils.contains(properties, name)) {
					continue;
				}
				if (exmap != null) {
					String mapName = exmap.get(name);
					if (mapName != null) {
						name = mapName;
					}
				}

				if (ArrayUtils.contains(keys, name)) {
					continue;
				}

				Class<?> t = PropertyUtils.getPropertyType(updateObject, name);

				// name为javabean属性名
				if (SqlUtils.checkedSimpleType(t)) {// 简单类型
					Object colValue = PropertyUtils.getProperty(updateObject,
							name);
					if (colValue != null) {
						colPart = colPart + name + "=?,";

						returnvParameters.put(index++, colValue);
					}

					// PropertyUtils.setProperty(bean, name, value);
				} else {
					String error = "update a illegal type:[" + t + "]";
					log.warn(error);
					//throw new DbException(error);
				}

			}// while

			colPart = colPart.substring(0, colPart.length() - 1);

			sql = sql + " " + colPart;
			String where = " where ";

			for (int n = 0; n < keys.length; n++) {
				Object value = PropertyUtils.getProperty(updateObject, keys[n]);
				where = where + keys[n] + "=?";
				if (n < keys.length - 1) {
					where = where + " and ";
				}
				returnvParameters.put(index++, value);
			}
			sql = sql + where;
			log.debug("generated sql:" + sql);
			return sql;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 生成插入sql语句
	 * 
	 * @param properties
	 *            需更新的属性，如果为空，表明更新所有主键属性以外的属性
	 * @param insertObject
	 *            需插入的对象
	 * @param returnvParameters
	 *            生成的参数，每个参数对应于生成语句的一个"?"字符
	 * @param exmap
	 *            名字映射，可能数据库里的字段映射到insertObject对象的属性时，
	 *            属性名可能为java语言的关键字，examp存放关键字的映射，通过此 映射可以转换成其他的名字
	 * @return
	 * @throws Exception
	 */
	public static String generateInsertSql(String[] properties,
			Object insertObject, Map<Integer, Object> returnvParameters,
			Map<String, String> exmap) throws Exception {
		String sql = "";
		String tableName = insertObject.getClass().getSimpleName();
		sql = "insert into " + tableName.toLowerCase() + "";
		String colPart = "(";
		String values = "values(";

		try {

			Map<?, ?> map = PropertyUtils.describe(insertObject);
			Set<?> set = map.keySet();
			Iterator<?> i = set.iterator();
			int index = 1;

			while (i.hasNext()) {

				String name = (String) i.next();

				if (name.equals("class")) {
					continue;
				}
				if (properties != null
						&& !ArrayUtils.contains(properties, name)) {
					continue;
				}
				if (exmap != null) {
					String mapName = exmap.get(name);
					if (mapName != null) {
						name = mapName;
					}
				}
				Class<?> t = PropertyUtils.getPropertyType(insertObject, name);

				// name为javabean属性名
				if (SqlUtils.checkedSimpleType(t)) {// 简单类型

					Object colValue = PropertyUtils.getProperty(insertObject,
							name);

					if (colValue == null)
						continue;

					colPart = colPart + name;
					values = values + "?";
					colPart = colPart + ",";
					values = values + ",";

					returnvParameters.put(index++, colValue);

					// PropertyUtils.setProperty(bean, name, value);
				} else {
					String error = "insert a illegal type:[" + t + "]";
					log.warn(error);
				}

			}// while

			colPart = colPart.substring(0, colPart.length() - 1) + ")";
			values = values.substring(0, values.length() - 1) + ")";
			sql = sql + " " + colPart + " " + values;

			log.debug("generated sql:" + sql);
			return sql;
		} catch (Exception e) {
			throw e;
		}

	}

	public static String registForStoredProc(Map<Integer, Object> vParameters,
			CallableStatement preStmt) throws SQLException {
		String paramStr = ""; // add by jda at 2007/12/15
		Set<Integer> keys = vParameters.keySet();
		List<Integer> keyList = new ArrayList<Integer>(keys);
		Collections.sort(keyList);

		if (vParameters != null && vParameters.size() > 0) {
			for (Integer key : keyList) {

				Object val = vParameters.get(key);
				if (!(val instanceof Class)) {
					val = val.getClass();
				}

				preStmt.registerOutParameter(key, javaType2sql.get(val));
				paramStr = paramStr + "[" + key + ":" + javaType2sql.get(val)
						+ "]";

			}
		}
		return paramStr;

	}

	public static String setToPreStatment(Map<Integer, Object> vParameters,
			PreparedStatement preStmt) throws SQLException {

		String paramStr = ""; // add by jda at 2007/12/15
		Set<Integer> keys = vParameters.keySet();
		List<Integer> keyList = new ArrayList<Integer>(keys);
		Collections.sort(keyList);//排序
		if (vParameters != null && vParameters.size() > 0) {
			for (Integer key : keyList) {

				Object obj = vParameters.get(key);

				if (obj instanceof String) {
					// preStmt.setString(j, (String) obj);
					preStmt.setString(key, (String) obj);
					paramStr = paramStr + "[" + key + ":" + (String) obj + "]";
				} else if (obj instanceof Integer) {
					preStmt.setInt(key, ((Integer) obj).intValue());
					paramStr = paramStr + "[" + key + ":"
							+ ((Integer) obj).toString() + "]";
				} else if (obj instanceof Long) {
					preStmt.setLong(key, ((Long) obj).longValue());
					paramStr = paramStr + "[" + key + ":"
							+ ((Long) obj).toString() + "]";
				} else if (obj instanceof Float) {
					preStmt.setFloat(key, ((Float) obj).floatValue());
					paramStr = paramStr + "[" + key + ":"
							+ ((Float) obj).toString() + "]";
				} else if (obj instanceof Double) {
					preStmt.setDouble(key, ((Double) obj).doubleValue());
					paramStr = paramStr + "[" + key + ":"
							+ ((Double) obj).toString() + "]";
				} else if (obj instanceof java.sql.Date) {
					preStmt.setDate(key, (java.sql.Date) obj);
					paramStr = paramStr + "[" + key + ":"
							+ ((java.sql.Date) obj).toString() + "]";
				} else if (obj instanceof java.util.Date) {
					java.sql.Timestamp tmsp = new java.sql.Timestamp(
							((java.util.Date) obj).getTime());
					preStmt.setTimestamp(key, tmsp);
					paramStr = paramStr + "[" + key + ":" + tmsp.toString()
							+ "]";
				} else {
					preStmt.setObject(key, obj);
					paramStr = paramStr + "[" + key + ":" + obj.toString()
							+ "]";

				}

			}
		}
		return paramStr;
	}

	/**
	 * 生产预处理的sql的debug语句，也就是把?字符替换成实际的参数，主要用于调试
	 * 
	 * @param sql
	 * @param vParameters
	 * @return
	 */
	public static String generateNotParmatersSql(String sql, Collection<Object> vParameters) {

		String paramStr = ""; // add by jda at 2007/12/15
		StringBuilder sb = new StringBuilder(sql);
		if (vParameters != null && vParameters.size() > 0) {
			Iterator it = vParameters.iterator();
			int i = 0, j = 0;
			int start = 0;

			while (it.hasNext()) {
				Object obj = it.next();
				j = ++i;
				int blen = sb.length();

				int m = sb.indexOf("?", start);

				if (m == -1)
					break;

				if (obj instanceof String) {
					sb.replace(m, m + 1, "'" + obj.toString() + "'");
				} else if (obj instanceof Integer) {
					sb.replace(m, m + 1, obj.toString());
				} else if (obj instanceof Long) {
					sb.replace(m, m + 1, obj.toString());
				} else if (obj instanceof Float) {
					sb.replace(m, m + 1, obj.toString());
				} else if (obj instanceof Double) {
					sb.replace(m, m + 1, obj.toString());
				} else if (obj instanceof java.sql.Date) {
					sb.replace(m, m + 1, "'"
							+ CTime.formatWholeDate(new java.util.Date(
									((java.sql.Date) obj).getTime())) + "'");
				} else if (obj instanceof java.util.Date) {
					sb
							.replace(
									m,
									m + 1,
									"'"
											+ CTime
													.formatWholeDate((java.util.Date) obj)
											+ "'");
				} else {

					sb.replace(m, m + 1, "'" + obj.toString() + "'");

				}

				int alen = sb.length();
				start = m + (alen - blen) + 1;

			}
		}
		return sb.toString();

	}

	public static String setToPreStatment(Collection<Object> vParameters,
			PreparedStatement preStmt) throws SQLException {

		String paramStr = ""; // add by jda at 2007/12/15
		if (vParameters != null && vParameters.size() > 0) {
			Iterator it = vParameters.iterator();
			int i = 0, j = 0;
			while (it.hasNext()) {
				Object obj = it.next();
				j = ++i;

				if (obj instanceof String) {
					// preStmt.setString(j, (String) obj);
					preStmt.setString(j, (String) obj);
					paramStr = paramStr + "[" + j + ":" + (String) obj + "]";
				} else if (obj instanceof Integer) {
					preStmt.setInt(j, ((Integer) obj).intValue());
					paramStr = paramStr + "[" + j + ":"
							+ ((Integer) obj).toString() + "]";
				} else if (obj instanceof Long) {
					preStmt.setLong(j, ((Long) obj).longValue());
					paramStr = paramStr + "[" + j + ":"
							+ ((Long) obj).toString() + "]";
				} else if (obj instanceof Float) {
					preStmt.setFloat(j, ((Float) obj).floatValue());
					paramStr = paramStr + "[" + j + ":"
							+ ((Float) obj).toString() + "]";
				} else if (obj instanceof Double) {
					preStmt.setDouble(j, ((Double) obj).doubleValue());
					paramStr = paramStr + "[" + j + ":"
							+ ((Double) obj).toString() + "]";
				} else if (obj instanceof java.sql.Date) {
					preStmt.setDate(j, (java.sql.Date) obj);
					paramStr = paramStr + "[" + j + ":"
							+ ((java.sql.Date) obj).toString() + "]";
				} else if (obj instanceof java.util.Date) {
					java.sql.Timestamp tmsp = new java.sql.Timestamp(
							((java.util.Date) obj).getTime());
					preStmt.setTimestamp(j, tmsp);
					paramStr = paramStr + "[" + j + ":" + tmsp.toString() + "]";
				} else {
					preStmt.setObject(j, obj);
					paramStr = paramStr + "[" + j + ":" + obj.toString() + "]";

				}
			}
		}
		return paramStr;
	}

}
