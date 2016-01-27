package com.keepjob.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		return "home/index";
	}
	@RequestMapping(value = "index2.html", method = RequestMethod.GET)
	public String toIndex2View(HttpSession session, HttpServletRequest request){
		return "home/index2";
	}
	
	@RequestMapping(value = "index3.html", method = RequestMethod.GET)
	public String toIndex3View(HttpSession session, HttpServletRequest request){
		return "home/index3";
	}
}
