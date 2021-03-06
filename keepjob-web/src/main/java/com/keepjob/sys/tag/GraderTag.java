package com.keepjob.sys.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.keepjob.common.enums.Grader;
import com.keepjob.common.util.StringUtils;

public class GraderTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id="";
	private String defaultValue ="";
	private String emptyValue ="";
	private String emptyText ="";
	private boolean disabled=false;
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspTagException {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer();
		
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
		for(Grader item : Grader.values()){
			sb.append("<option value=\""+item.getCode()+"\"");
			if(StringUtils.trim(item.getCode()).equals(StringUtils.trim(this.defaultValue))){
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
	}

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
