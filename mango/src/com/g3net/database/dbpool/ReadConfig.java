/*
 * Copyright 2004-2008 the original author or authors.
 * Licensed under 3G门户
 */
package com.g3net.database.dbpool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.g3net.tool.ArrayUtils;
import com.g3net.tool.CollectionUtils;
import com.g3net.tool.Path;



/**
 * @author 江威
 * @email jiang-wei@3g.net.cn/weijiang8410@163.com
 * @since 1.0
 */
public class ReadConfig {

	private  Logger log = Logger.getLogger(ReadConfig.class);
	private  Map<String,Map<String,String>>  properties=null;
	private  Map<String,String[]> trans=null;
	private static ReadConfig config=new ReadConfig();
	private ReadConfig(){
		parse();
	}
	public static ReadConfig getInstance(){
		return config;
	}
	public Map<String,Map<String,String>> getProperties(){
		return properties;
	}
	public Map<String,String[]> getTrans(){
		return trans;
	}
	/**
	 * 读配置文件
	 * 
	 * @return
	 */
	private  void parse() {
		
		Map<String,Map<String,String>> maps=
			new ConcurrentHashMap<String,Map<String,String>>();
		Map<String,String[]> disTransMaps=new ConcurrentHashMap<String,String[]>();
		
		try {
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			//File file = new File(Path.getClassPath() + "config.xml");
			//Document doc = db.parse(file);
			Document doc = db.parse(Path.getResource("/dbpool.xml"));
			NodeList configList = doc.getElementsByTagName("dbpool");
			for(int m=0;configList!=null && m<configList.getLength(); m++ ){
				Element config_el=(Element) configList.item(m);
				String configName=config_el.getAttribute("name");
				String onlyForDis= config_el.getAttribute("onlyForDis");
				String type =config_el.getAttribute("type");
				NodeList nList = config_el.getElementsByTagName("property");
				Map<String,String> map = new HashMap<String,String>();
				maps.put(configName, map);
				map.put("onlyForDis", onlyForDis);
				map.put("type", type);
				for (int i = 0; nList != null && i < nList.getLength(); i++) {
					
					Element propertye_el = (Element) nList.item(i);
					String name=propertye_el.getAttribute("name");
	
					String value=propertye_el.getTextContent();
					map.put(name, value);
					
				}
			}
			NodeList disTransactions=doc.getElementsByTagName("dis-transaction");
			for(int m=0;disTransactions!=null && m<disTransactions.getLength(); m++ ){
				Element trans=(Element) disTransactions.item(m);
				String name=trans.getAttribute("name");
				String[] values = trans.getTextContent().split(",");
				values=ArrayUtils.trim(values);
				disTransMaps.put(name, values);
			}
			
		} catch (Exception e) {
			log.error("读取XML文档异常。。。。", e);
		}
		properties=maps;
		trans=disTransMaps;
		
	}

	/**
	 * 测试配置文件
	 */
	public void testReadCfg() {

	}

	public static void main(String[] args) {
//		ReadConfig.parse();
//		
//		log.info(CollectionUtils.toString(ReadConfig.properties));
//		log.info(CollectionUtils.toString(ReadConfig.trans));
	}
}
