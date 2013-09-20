package com.g3net.database;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.g3net.database.sql.SqlUtils;

public class QueryMapNestStrategy {

	public static Object setOne2One(Object nestedObj, String[] toPros,
			String prefix, DataBaseSet rs) throws Exception {

		if (toPros != null) {// 部分映射

			for (int j = 0; toPros != null && j < toPros.length; j++) {

				String toPro = toPros[j];

				Class type = PropertyUtils.getPropertyType(nestedObj, toPro);
				Object value = SqlUtils.getValueFromResult(type,
						prefix ,toPro, rs.getResultSet(),DataBaseKeyMap.getMap());

				PropertyUtils.setProperty(nestedObj, toPro, value);
			}
		} else { // 全部映射
			Map map = PropertyUtils.describe(nestedObj);
			Set set = map.keySet();
			Iterator i = set.iterator();
			while (i.hasNext()) {
				String name = (String) i.next();

				if (name.equals("class"))
					continue;
				// //
				Class type = PropertyUtils.getPropertyType(nestedObj, name);
				//System.err.print(name+":"+key);
				Object value = SqlUtils.getValueFromResult(type, prefix ,name,
						rs.getResultSet(),DataBaseKeyMap.getMap());

				PropertyUtils.setProperty(nestedObj, name, value);

			}
			
			//System.err.println("\n");
		}

		return nestedObj;

	}

	public static String getKeyValue(Object bean, String key) throws Exception {
		String[] nestKeys = key.split(";");
		String nestKeyValueStr = "";
		for (int q = 0; q < nestKeys.length; q++) {
			Object obj = PropertyUtils.getProperty(bean, nestKeys[q].trim());
			if (obj == null)
				return null;
			nestKeyValueStr = nestKeyValueStr + obj;
			if (q == nestKeys.length - 1) {
				break;
			}
			nestKeyValueStr = nestKeyValueStr + ";";
		}
		return nestKeyValueStr;
	}

	public static String getKeyValue(Object bean, String sqlPrefix, String key,
			DataBaseSet rs) throws Exception {

		String nestkeyStr = "";
		String[] keys = key.split(";");
		for (int n = 0; n < keys.length; n++) {
			String nkey = keys[n].trim();
			Class keyClass = PropertyUtils.getPropertyType(bean, nkey);

			Object keyValue = SqlUtils.getValueFromResult(keyClass, sqlPrefix
					, nkey, rs.getResultSet(),DataBaseKeyMap.getMap());
			if (keyValue == null) {
				return null;
			}
			if (n == keys.length - 1) {
				nestkeyStr = nestkeyStr + keyValue;
			} else {
				nestkeyStr = nestkeyStr + keyValue + ";";
			}

		}
		return nestkeyStr;

	}

	/**
	 * 
	 * @param bean
	 * @param nestBeanName
	 * @param keyArray
	 * @param keyValue
	 *            以英文分号隔开
	 * @return
	 */
	public static boolean equals(Object bean, String[] keyNames, String keyValue) {
		boolean b = false;
		try {
			String toValues = "";
			for (int i = 0; i < keyNames.length; i++) {
				Object value = PropertyUtils.getSimpleProperty(bean,
						keyNames[i]);

				if(value==null)
					return false;
				if (i == keyNames.length - 1) {
					toValues = toValues + value;
				} else {
					toValues = toValues + value + ";";
				}
			}

			if (keyValue.equals(toValues)) {
				return true;
			}
		} catch (Exception e) {
		}

		return false;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "ssss;;sss";
		
	}

}
