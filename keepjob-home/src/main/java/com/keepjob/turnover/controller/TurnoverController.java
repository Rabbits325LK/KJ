package com.keepjob.turnover.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.keepjob.turnover.Turnover;
import com.keepjob.turnover.TurnoverHandler;

@RequestMapping("/turnover")
@Controller
public class TurnoverController {
	
	@Autowired
	private TurnoverHandler turnoverHandler;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
    public String indexView(HttpSession session, HttpServletRequest request, Model model ) {
		List<Turnover> turnovers = turnoverHandler.findTurnovers();
		model.addAttribute("result", turnovers);
		return "/turnover/index";
	}
	
	
	

}
