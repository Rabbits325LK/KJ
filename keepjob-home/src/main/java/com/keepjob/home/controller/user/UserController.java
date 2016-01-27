package com.keepjob.home.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.util.json.JSONMessage;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Gson gson;
	
	@RequestMapping( value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		return "user/index";
	}
	
	@RequestMapping( value = "/login.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String toLogin(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "searchCode", required = true)String searchCode,
			@RequestParam(value = "password", required = true)String password){
		
		
		return gson.toJson(JSONMessage.createSuccessMessage());
	}
}
