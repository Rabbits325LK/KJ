package com.keepjob.sys.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.keepjob.common.Constant;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.sys.user.User;


public final class WebUtils {

	public static String getRealPath(HttpServletRequest request,String relativePath){
		return request.getServletContext().getRealPath(relativePath);
	}
	
	public static User getCurrentUser(ServletRequest request) {
		HttpSession session =((HttpServletRequest)request).getSession();
		if (null == session) {
			throw new ApplicationException("当前HttpSession对象不存在。");
		}
		Object object = null;
		if ((object = session.getAttribute(Constant.USER_KEY)) == null) {
			throw new ApplicationException("当前用户信息不存在。");
		}
		return ((User) object);
	}

}
