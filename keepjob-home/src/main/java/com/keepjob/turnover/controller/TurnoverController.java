package com.keepjob.turnover.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.keepjob.turnover.TurnoverHandler;

@RequestMapping("/turnover")
@Controller
public class TurnoverController {
	
	@Autowired
	private TurnoverHandler turnoverHandler;
	
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
	public String indexView(HttpSession session, HttpServletRequest request) {
		return "/turnover/index";
	}
	

}
