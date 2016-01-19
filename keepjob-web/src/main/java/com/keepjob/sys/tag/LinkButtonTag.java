package com.keepjob.sys.tag;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.keepjob.common.Constant;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.user.User;

@SuppressWarnings("serial")
public class LinkButtonTag extends TagSupport {
	private String id;
	private String url;
	private String title;
	private String icon;
	private String styleClass;
	private String options;
	private String click;
	private boolean security = true;
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspTagException {
		boolean mark = false;
		JspWriter out = this.pageContext.getOut();
		User user = (User)this.pageContext.getSession().getAttribute(Constant.USER_KEY);
		if(null == user){
			throw new ApplicationException("当前登录用户未登陆.");
		}
		StringBuffer sb = new StringBuffer();
		if(this.security){
			for(String item : user.getResourceUrls()){
				if(StringUtils.trimToEmpty(item).indexOf(StringUtils.trimToEmpty(url))>=0){
					mark = true;
					break;
				}
			}
		}

		if((this.security && mark) || !this.security){
			sb.append("<img id=\"");
			sb.append(StringUtils.trim(id));
			sb.append("\"");
			if(StringUtils.isNotEmpty(click)){
				sb.append(" onclick=\"");
				sb.append(StringUtils.trim(click));
				sb.append("\"");
			}
			if(StringUtils.isNotEmpty(icon)){
				sb.append(" src=\"");
				sb.append(StringUtils.trim(icon));
				sb.append("\"");
			}
			if(StringUtils.isNotEmpty(title)){
				sb.append(" title=\"");
				sb.append(StringUtils.trim(title));
				sb.append("\"");
			}
			if(StringUtils.isNotEmpty(styleClass)){
				sb.append(" class=\"");
				sb.append(StringUtils.trim(styleClass));
				sb.append("\"");
			}
			if(StringUtils.isNotEmpty(options)){
				sb.append(" data-options=\"");
				sb.append(StringUtils.trim(options));
				sb.append("\"");
			}
			sb.append("/>");
		}
		
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
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
