package com.g3net.tool;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class ReadXML {
	// private String filename;
	private static Logger log = Logger.getLogger(ReadXML.class);
	private Document doc;

	public Document getDocumnet() {
		return doc;
	}

	public ReadXML(String filename) {

		this(filename, false);
	}

	public ReadXML(String filename, boolean validate) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			factory.setValidating(validate);
			doc = db.parse(new File(filename));

			factory = null;

		} catch (Exception e) {
			log.error("", e);
			// e.printStackTrace();
		}
	}
	
	public ReadXML(StringBuilder xml, boolean validate) throws Exception{
		this(new ByteArrayInputStream(xml.toString().getBytes("utf-8")),"utf-8", validate);
	
	}

	public ReadXML(InputStream inputStream, boolean validate) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(validate);
			doc = factory.newDocumentBuilder().parse(inputStream);
			factory = null;
		} catch (Exception e) {
			log.error("", e);
		}
	}
	public ReadXML(InputStream inputStream,String charset, boolean validate) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(validate);
			InputSource is=new InputSource(inputStream);
			is.setEncoding(charset);
			doc = factory.newDocumentBuilder().parse(is);
			factory = null;
		} catch (Exception e) {
			log.error("", e);
		}
	}
	public ReadXML(InputStream in) {
		this(in, false);
	}

	public void Close() {

		doc = null;
	}

	public String readValueByTagName(String nodename) {
		String result = null;
		String node = nodename;
		try {
			NodeList list = doc.getElementsByTagName(node);
			Element element = (Element) list.item(0);
			result = element.getTextContent();

			element = null;
			list = null;
			node = null;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
		}
		return result;
	}

	public String readValueByTagName(Element findElement, String nodename) {
		String result = null;
		String node = nodename;
		try {
			NodeList list = findElement.getElementsByTagName(node);
			Element element = (Element) list.item(0);
			result = element.getTextContent();

			element = null;
			list = null;
			node = null;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
		}
		return result;
	}

	public NodeList getNodeListByTagName(String nodename) {
		try {
			NodeList list = doc.getElementsByTagName(nodename);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
			return null;
		}
	}

	public NodeList getNodeListByTagName(Element findElement, String nodename) {
		try {
			NodeList list = findElement.getElementsByTagName(nodename);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
			return null;
		}
	}

	public List<Element> getNodeListByTagNameAndAtrribute(Element findElement,
			String nodename, String arributeName, String arrbuteValue) {
		List<Element> newList = new ArrayList<Element>();
		try {
			NodeList list = findElement.getElementsByTagName(nodename);
			for (int i = 0; i < list.getLength(); i++) {
				Element e = (Element) list.item(i);
				if (arributeName != null) {
					String av = e.getAttribute(arributeName);
					if (arrbuteValue != null) {
						if (av!=null && av.equals(arrbuteValue)) {
							newList.add(e);
						}
					}else{
						if(av!=null){
							newList.add(e);
						}
					}
				}else{
					newList.add(e);
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
		}
		return newList;
	}
	public List<Element> getNodeListByTagNameAndAtrribute(Element findElement,
			String nodename, String[] arributeNames, String[] arrbuteValues) {
		List<Element> newList = new ArrayList<Element>();
		try {
			NodeList list = findElement.getElementsByTagName(nodename);
			for (int i = 0; i < list.getLength(); i++) {
				Element e = (Element) list.item(i);
				if (arributeNames != null) {
					for(int n=0; n<arributeNames.length; n++){
						String arributeName=arributeNames[n];
						String av = e.getAttribute(arributeName);
						if (arrbuteValues != null) {
							if (av!=null && av.equals(arrbuteValues[n])) {
								newList.add(e);
							}
						}else{
							if(av!=null){
								newList.add(e);
							}
						}
					}
				}else{
					newList.add(e);
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
		}
		return newList;
	}
	public String[] readContentByTagName(Element findElement, String nodename) {
		String[] result = null;
		try {

			NodeList list = findElement.getElementsByTagName(nodename);
			result = new String[list.getLength()];
			for (int i = 0; i < result.length; i++) {
				result[i] = list.item(i).getTextContent();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
			result = new String[0];
		}
		return result;
	}

	public String[] readContentByTagName(String nodename) {

		String[] result = null;
		try {
			NodeList list = this.doc.getElementsByTagName(nodename);
			result = new String[list.getLength()];
			for (int i = 0; i < result.length; i++) {
				result[i] = list.item(i).getTextContent();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("", e);
			result = new String[0];
		}
		return result;

	}

	/**
	 * 过虑xml的无效字符。
	 * <p/>
	 * <ol>
	 * <li>0x00 - 0x08</li>
	 * <li>0x0b - 0x0c</li>
	 * <li>0x0e - 0x1f</li>
	 * </ol>
	 * 
	 */
	public static String filter(String xmlStr) {
		StringBuilder sb = new StringBuilder();
		char[] chs = xmlStr.toCharArray();
		// log.info("filter before=" +chs.length);
		for (char ch : chs) {
			if ((ch >= 0x00 && ch <= 0x08) || (ch >= 0x0b && ch <= 0x0c)
					|| (ch >= 0x0e && ch <= 0x1f)) {
				// eat...
			} else {
				sb.append(ch);
			}
		}
		// log.info("filter after=" +sb.length());
		return sb.toString();
	}

	public static String toString(Document doc, String encoding, boolean indent) {
		return toString(doc, "", "", indent, encoding);
	}

	public static String toString(Document doc, String systemIdentifier,
			String publicIdentifier, boolean indent, String encoding) {
		// construct the "do nothing" transformation
		try {
			TransformerFactory tf= TransformerFactory.newInstance();
		
			Transformer t = tf.newTransformer();
			Properties properties = t.getOutputProperties(); 

			properties.setProperty(OutputKeys.OMIT_XML_DECLARATION,"no");
			properties.setProperty(OutputKeys.INDENT, indent ? "yes" : "no");
			//properties.setProperty(OutputKeys.METHOD, "xml");
			properties.setProperty(OutputKeys.ENCODING, encoding);
			t.setOutputProperties(properties);
			
			//ByteArrayOutputStream outs = new ByteArrayOutputStream();
			Writer writer=new StringWriter();
			Result result=new StreamResult(writer);
			doc.setXmlStandalone(true);
			doc.setXmlVersion("1.0");
			
			t.transform(new DOMSource(doc), result);
			return writer.toString();
		} catch (Exception e) {
			log.error("", e);
		} 
		return null;
	}

	

	public static void main(String[] args) {
		log.info(IOUtils.toString(Path.getResource("/test2.xml"), true));
		ReadXML re=new ReadXML(Path.getResource("/test2.xml"),false);
		List<Element> list=re.getNodeListByTagNameAndAtrribute(re.getDocumnet().getDocumentElement(), 
				"param", "name","to");
		Map<String ,String[]> map=new HashMap<String,String[]>();
		for(int i=0; i<list.size(); i++){
			Element e=list.get(i);
			map=CollectionUtils.putMap(map, e.getAttribute("name"), e.getAttribute("value"));
		}
		log.info(ObjectUtils.toString(map));
		
	}
}
