package com.keepjob.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserHandler userHandler;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session , HttpServletRequest request, Model model){
		User user = userHandler.getUser(1);
		model.addAttribute("user", user);
		model.addAttribute("json",gson.toJson(user));
		return "home/index";
	}
}
