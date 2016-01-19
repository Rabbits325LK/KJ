package com.keepjob.sys.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.keepjob.common.spring.SpringBeanFactory;
import com.keepjob.common.util.StringUtils;

@SuppressWarnings("serial")
public class BusinessTag extends TagSupport {
	private String id="";
	private String defaultValue ="";
	private String emptyValue ="";
	private String emptyText ="";
	private boolean disabled=false;
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_INCLUDE;
	}
	
	/*public int doEndTag() throws JspTagException {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		
		DictionaryHandler dictionaryHandler = (DictionaryHandler)SpringBeanFactory.getBean("dictionaryHandler");
		List<Business> businesss = dictionaryHandler.findBusinesss();
		sb.append("<select name=\""+id+"\" id=\""+id+"\" class=\"easyui-combobox\"");
		if(this.isDisabled()){
			sb.append(" disabled");
		}
		sb.append(">\n");
		sb.append("<option value=\"\">请选择</option>");
		if(StringUtils.isNotEmpty(emptyValue) && StringUtils.isNotEmpty(emptyText)){
			sb.append("<option value=\""+emptyValue+"\"");
			if(StringUtils.trim(emptyValue).equals(StringUtils.trim(this.defaultValue))){
				sb.append(" selected");
			}
				
			sb.append(">"+emptyText+"</option>");
		}
		for(Business item : businesss){
			sb.append("<option value=\""+item.getUniqueCode()+"\"");
			if(StringUtils.trim(item.getUniqueCode()).equals(StringUtils.trim(this.defaultValue))){
				sb.append(" selected");
			}
				
			sb.append(">");
			sb.append(item.getName());
			sb.append("</option>\n");
		}
		sb.append("</select>");
		
		try{
			out.print(sb.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getEmptyValue() {
		return emptyValue;
	}

	public String getEmptyText() {
		return emptyText;
	}

	public void setEmptyValue(String emptyValue) {
		this.emptyValue = emptyValue;
	}

	public void setEmptyText(String emptyText) {
		this.emptyText = emptyText;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
