package com.keepjob.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;


public class XmlUtils {
	private XmlUtils(){
		
	}
	/**
	 * 创建
	 * @return
	 */
	public static Document createDoucment(){
		return DocumentFactory.getInstance().createDocument();
	}
	/**
	 * 创建根节点
	 * @param name
	 * @param value
	 * @return
	 */
	public static Element createElement(String name, String value){
		if(StringUtils.isNotEmpty(value)){
			return DocumentHelper.createElement(name).addText(value);
		}else{
			return DocumentHelper.createElement(name);
		}
	}
	/**
	 * 创建节点
	 * @param doc
	 * @param name
	 * @return
	 */
	public static Element createRootElement(Document doc, String name){
		Element root = createElement(name, "");
		doc.setRootElement(root);
		return root;
	}
	/**
	 * 添加子元素
	 * @param parent
	 * @param name
	 * @param value
	 */
	public static void appendChildElement(Element parent, String name, String value){
		parent.add(createElement(name, value));
	}
	
	/**
	 * 对象转化为XML元素
	 * @param parent
	 * @param object
	 * @param propertys
	 */
	public static void toElements(Element parent, Object object, Map<String, String> propertys){
		String key = "";
		if(null != propertys && !propertys.isEmpty()){
			try{
				Iterator<String> it = propertys.keySet().iterator();
				while(it.hasNext()){
					key = it.next();
					String value = BeanUtils.getProperty(object, key);
					appendChildElement(parent, propertys.get(key), value);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	/**
	 * 对象转化为XML元素
	 * @param parent
	 * @param objects
	 * @param propertys
	 */
	public static void toElements(Element parent, List<Object> objects, Map<String, String> propertys){
		Element el =null;
		if(null != objects && !objects.isEmpty()){
			for(Object object : objects){
				el = createElement(object.getClass().getName(), "");
				parent.add(el);
				
				toElements(el, object, propertys);
			}
		}
	}
	/**
	 * 对象转化为XML元素
	 * @param parent
	 * @param elName
	 * @param objects
	 * @param propertys
	 */
	public static void toElements(Element parent, String elName, List<Object> objects, Map<String, String> propertys){
		Element el =null;
		if(null != objects && !objects.isEmpty()){
			for(Object object : objects){
				el = createElement(elName, "");
				parent.add(el);
				
				toElements(el, object, propertys);
			}
		}
	}
	/**
	 * 解析XML串
	 * @param xml
	 * @return
	 */
	public static Document parseXml(String xml){
		try{
			return DocumentHelper.parseText(xml);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定元素名元素
	 * @param doc
	 * @param name
	 * @return
	 */
	public static Element getElement(Document doc, String name){
		List<Element> els = getElements(doc, name);
		if(null != els && !els.isEmpty()){
			return els.get(0);
		}
		return null;
	}
	
	/**
	 * 获取指定元素名元素集
	 * @param doc
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Document doc, String name){
		XPath xpath = DocumentHelper.createXPath(name);
	    List<Element> result = (List<Element>)xpath.selectNodes(doc);
	    return result;
	}
	/**
	 * 获取指定元素的Text值
	 * @param doc
	 * @param name
	 * @return
	 */
	public static String getElementValue(Document doc, String name){
		Element el = getElement(doc, name);
		if(null != el){
			return el.getTextTrim();
		}
		return "";
	}
	/**
	 * 从Element里获取指定元素的Text值
	 * @param e
	 * @param name
	 * @return
	 */
	public static String getElementValue(Element e, String name){
		Element el = e.element(name);
		if(null != el){
			return el.getTextTrim();
		}
		return "";
	}
	
	/**
	 * 获取某一节点下所有子节点的属性名称与对应的值放入MAP
	 * @param xmlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map <String, String> getResults (String xmlString)
    {
        if (xmlString == null){
            return null;
        }
      
		Map <String, String> map = new HashMap <String, String> ();
		try {
			Element element = null;
			Document doc = XmlUtils.parseXml(xmlString);
			Element root = doc.getRootElement();

			List<Element> elements = XmlUtils.getElements(doc, root.getName());
			
			Iterator<Element> it = elements.iterator();
	        if(it.hasNext()){
	        	element = it.next();
	        	List<Element> ets = element.content(); 
	        	for(Element et : ets){
	        		String key = et.getName ();
	                String value = et.getText ();
	                map.put (key, value);
	        	}
            }
		}catch (Exception e) {
			e.printStackTrace();
		}		
        return map;
    }
	
	public static void main(String args[]){
		//String xmlParameter="<?xml version=\"1.0\" encoding=\"GBK\"?><otherparam><sex>0</sex><age>34</age><clinicid>1242</clinicid><uaccount/><caseName>周小舟7</caseName><caseBirthday/><caseHeight>170</caseHeight><pid>11111</pid><caseSource>5</caseSource><orgName>空中医院1</orgName><caseAvoirdupois>111</caseAvoirdupois><phone>11111</phone><caseRace/><caseAddr>SFSFSFSDFDS</caseAddr><comment>SDFSF</comment><freq/><scale/><paced>0</paced><unit/><gatherNo/><apparatus/><appNum/></otherparam>";
		
		String xmlParameter="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
		xmlParameter=xmlParameter+"<kzyy><userinfo><username>pacs_test</username><password>000000</password></userinfo><organno>0001</organno><applyno>C2015122900026</applyno><medicalrecordno>20151229000005</medicalrecordno><name>zfXQobKo</name><sexcode>1</sexcode><birthday>1981-10-12 00:00:00</birthday><idcard></idcard><officecode>3</officecode><clinicalexpression></clinicalexpression><inpatientno></inpatientno><inpatientarea></inpatientarea><badno></badno><doctorname>MDA=</doctorname><costs>48.00</costs><costsstatus>1</costsstatus><source>1</source><checktype>DR</checktype><positions><largeClass><code>1000</code><name>0Ng=<name><subdClassList><subdClass><code>7006</code><name>0Niyv9X9suDOuw==<name></subdClass></subdClassList></largeClass></positions></kzyy>";
		
		Document doc = XmlUtils.parseXml(StringUtils.trim(xmlParameter));
		System.out.println(XmlUtils.getElementValue(doc, "/otherparam/caseAddr"));
		System.out.println(NumberUtils.replaceInteger(XmlUtils.getElementValue(doc, "/otherparam/age")));
		System.out.println(XmlUtils.getElementValue(doc, "/otherparam/caseBirthday"));
		System.out.println(XmlUtils.getElementValue(doc, "/otherparam/clinicid"));
		System.out.println(XmlUtils.getElementValue(doc, "/otherparam/pid"));
		System.out.println(XmlUtils.getElementValue(doc, "/otherparam/phone"));
		System.out.println(StringUtils.isNotEmpty(XmlUtils.getElementValue(doc, "/otherparam/caseHeight"))? Double.parseDouble(XmlUtils.getElementValue(doc, "/otherparam/caseHeight")) : null);
		System.out.println(StringUtils.isNotEmpty(XmlUtils.getElementValue(doc, "/otherparam/caseRace"))? Double.parseDouble(XmlUtils.getElementValue(doc, "/otherparam/caseRace")) : null);

	}
	
	
}
