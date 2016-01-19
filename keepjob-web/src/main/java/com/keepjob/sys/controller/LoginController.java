package com.keepjob.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepjob.common.Constant;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.IPUtils;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ResourceHandler resourceHandler;
	
	@RequestMapping("/loginsuccess")
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication auth = securityContext.getAuthentication();
		String userName = auth.getName();
		 //自定义设置信息
	    User user = userHandler.getUserByLoginName(userName);
		User updateUser = new User();
		
	    user.setLastLoginIp(IPUtils.getIPV4(request));
		user.setLastLoginDate(DateUtils.getCurrentDateTime());
		user.setUpdateTime(DateUtils.getCurrentDateTime());
		
		updateUser.setId(user.getId());
		updateUser.setLastLoginIp(IPUtils.getIPV4(request));
		updateUser.setLastLoginDate(DateUtils.getCurrentDateTime());
		updateUser.setUpdateTime(DateUtils.getCurrentDateTime());
		this.userHandler.updateUser(updateUser, updateUser);
		user.setPassword(null);// 设置密码为空，避免密码更新
		List<Resource> list=resourceHandler.findResourcesByUserCode(user.getUniqueCode());

		List<String> strList = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				strList.add(list.get(i).getUrl());
			}
		}
		user.setResourceUrls(strList);
		session.setAttribute(Constant.USER_KEY, user);
		logger.info(" 用户{0}登录系统成功，登录IP:{1}.", user.getLoginCode(), user.getLastLoginIp());
		
		return "redirect:/main/main.html";
	}

	@RequestMapping({"/","/login.html"})
	public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam(required=false) String error){
		if("expired".equals(error)) {
			error = "<font color='red'>该账号在别处登陆，您已被强制下线。<br/>如果不是您的操作，请及时修改密码。</font>";
			request.setAttribute("error", error);
		}else{
			Object object = session.getAttribute(Constant.USER_KEY);
			if(null != object){
				return "redirect:/main/main.html";
			}
		}
		return "login";
	}
	
}
