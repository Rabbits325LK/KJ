package com.keepjob.sys.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.keepjob.common.Constant;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.user.User;

@SuppressWarnings("serial")
public class ToolBarTag extends TagSupport {
	private String id="toolBar";
	private String styleClass;
	private String options;
	private List<Button> buttons = null;
	
	public ToolBarTag(){
		super();
		buttons = new ArrayList<Button>(); 
	}
	public void addButton(Button button){
		buttons.add(button);
	}
	
	public void addButton(String id, String name, String url, String styleClass, String options, boolean security){
		Button button = new Button(id, name, url, styleClass, options, security);
		buttons.add(button);
	}
	
	public void addButton(String id, String name, String url, String styleClass, String options){
		Button button = new Button(id, name, url, styleClass, options);
		buttons.add(button);
	}
	
	public void addButton(String id, String name, String url, String options){
		Button button = new Button(id, name, url, options);
		buttons.add(button);
	}
	
	public int doStartTag() throws JspTagException {
		buttons.clear();
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
		if(CollectionUtils.isNotEmpty(buttons)){
			sb.append("<div id=\"");
			sb.append(id);
			sb.append("\"");
			if(StringUtils.isNotEmpty(styleClass)){
				sb.append(" class=\"");
				sb.append(styleClass);
				sb.append("\"");
			}
			
			if(StringUtils.isNotEmpty(options)){
				sb.append(" data-options=\"");
				sb.append(options);
				sb.append("\"");
			}
			sb.append(">");
			for(Button button : this.buttons){
				if(button.isSecurity()){
					for(String url : user.getResourceUrls()){
						if(StringUtils.trimToEmpty(url).indexOf(StringUtils.trimToEmpty(button.getUrl()))>=0){
							mark = true;
							break;
						}
					}
						
					if(mark){
						sb.append(button.toString());
					}
					mark = false;
				}else{
					sb.append(button.toString());
				}	
			}
			sb.append("</div>");
		}
		
		try{
			out.print(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	class Button{
		private String id;
		private String url;
		private String name;
		private String styleClass;
		private String options;
		private boolean security = true;
		
		public Button(String id, String name, String url, String options){
			this.id = id;
			this.name = name;
			this.options = options;
			this.styleClass = "easyui-linkbutton";
			this.url = url;
		}
		
		public Button(String id, String name, String url, String styleClass, String options){
			this.id = id;
			this.name = name;
			this.options = options;
			this.styleClass = styleClass;
			this.url = url;
		}
		
		public Button(String id, String name, String url, String styleClass, String options, boolean security){
			this.id = id;
			this.name = name;
			this.options = options;
			this.styleClass = styleClass;
			this.url = url;
			this.security = security;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
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
		
		public String toString(){
			StringBuffer sb = new StringBuffer();
			sb.append("<a id=\"");
			sb.append(StringUtils.trim(id));
			sb.append("\" href=\"javascript:;\"");
			if(StringUtils.isNotEmpty(styleClass)){
				sb.append(" class=\"");
				sb.append(StringUtils.trim(styleClass));
				sb.append("\"");
			}else{
				sb.append(" class=\"easyui-linkbutton\"");
			}
			if(StringUtils.isNotEmpty(options)){
				sb.append(" data-options=\"");
				sb.append(StringUtils.trim(options));
				sb.append("\"");
			}
			sb.append(">");
			sb.append(StringUtils.trim(name));
			sb.append("</a>");
			
			return sb.toString();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}