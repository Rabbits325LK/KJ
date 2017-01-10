package com.keepjob.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.employee.Employee;
import com.keepjob.core.employee.EmployeeHandler;
import com.keepjob.core.use.UseShipRecord;
import com.keepjob.core.use.UseShipRecordHandler;
import com.keepjob.wechat.KeepJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sword.wechat4j.util.AnalysisCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/weChat")
public class InitWeChatController {

	@Autowired
	private Gson gson;
	@Autowired
	private UseShipRecordHandler useShipRecordHandler;
	@Autowired
	private EmployeeHandler employeeHandler;

	@RequestMapping(value = "/init.html")
	@ResponseBody
	public void init(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//		String resultToken = request.getParameter("token");
//		
//		System.out.println(resultToken);
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("init wechat");
		KeepJob keepJob = new KeepJob(request);
		
		String result = keepJob.execute();
		System.out.println(result);
		response.getOutputStream().write(result.getBytes());
		//ModelAndView model = new ModelAndView();
		//return result;
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("JSON","JSON");
		return "wechat/index";
	}

	@RequestMapping(value = "/register.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String register(HttpSession session, HttpServletRequest request, UseShipRecord record, String employeeCode) {
		try {
			Employee employee = this.employeeHandler.get(employeeCode);
			this.useShipRecordHandler.create(record, employee);
			return gson.toJson(JSONMessage.createSuccessMessage());
 		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
}


