package com.g3net.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXML {

	private Document doc = null;

	public CreateXML() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.newDocument();
		

	}

	public Element createRoot(String tagName, Map<String, String> attributes) {
		Element el = this.createElement(tagName, attributes, null);
		doc.appendChild(el);
		
		return el;
	}

	public Element createElement(String tagName,
			Map<String, String> attributes, String contentText) {
		Element element = doc.createElement(tagName);
		if (attributes != null) {
			Set<String> keys = attributes.keySet();
			for (String key : keys) {
				element.setAttribute(key, attributes.get(key));
			}
		}
		if (StringUtils.hasText(contentText)) {
			element.appendChild(doc.createTextNode(contentText));
		}

		return element;
	}

	public void appendChildElement(Element fatherElement, Element childElement){
		fatherElement.appendChild(childElement);
	}
	public void appendChildText(Element fatherElement,String childText){
		fatherElement.appendChild(doc.createTextNode(childText));
	}
	
	public void addAttribute(Element element,String name,String value){
		element.setAttribute(name, value);
	}
	public void addAttribute(Element element,Map<String, String> attributes){
		if (attributes != null) {
			Set<String> keys = attributes.keySet();
			for (String key : keys) {
				element.setAttribute(key, attributes.get(key));
			}
		}
	}
	public  String toString(String encoding, boolean indent) {
		
		return toString( "", "", indent, encoding);
	}
	public String toString( String systemIdentifier,
			String publicIdentifier, boolean indent, String encoding){
		
		return ReadXML.toString(doc, systemIdentifier, publicIdentifier, indent, encoding);
		
	}

}
