package com.keepjob.wechat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.keepjob.wechat.KeepJob;

@Controller
@RequestMapping("/weChat")
public class InitWeChatController {

	@RequestMapping(value = "/init.html")
	@ResponseBody
	public String init(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		String resultToken = request.getParameter("token");
//		
//		System.out.println(resultToken);
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("init wechat");
		KeepJob keepJob = new KeepJob(request);
		
		String result = keepJob.execute();
		System.out.println(result);
		//response.getOutputStream().write(result.getBytes());
		//ModelAndView model = new ModelAndView();
		return result;
	}
}


