package com.keepjob.sys.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class ToolBarButtonTag extends TagSupport {
	private String id;
	private String url;
	private String name;
	private String styleClass;
	private String options;
	private boolean security = true;
	
	public int doEndTag() throws JspTagException{
		ToolBarTag parent = (ToolBarTag)findAncestorWithClass(this, ToolBarTag.class);
		parent.addButton(id, name, url, styleClass, options, security);
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public boolean isSecurity() {
		return security;
	}

	public void setSecurity(boolean security) {
		this.security = security;
	}
}
