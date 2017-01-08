package com.keepjob.turnover.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.turnover.Turnover;
import com.keepjob.turnover.TurnoverHandler;

@RequestMapping("/turnover")
@Controller
public class TurnoverController {
	
	@Autowired
	private TurnoverHandler turnoverHandler;
	@Autowired
	private Gson gson;
	
	/**
	 * 跳转主页面
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index.html", method = RequestMethod.GET)
    public String indexView(HttpSession session, HttpServletRequest request, Model model ) {
		List<Turnover> turnovers = turnoverHandler.findTurnovers();
		model.addAttribute("result", turnovers);
		return "/turnover/index";
	}
	
	/**
	 * 跳转新增修改页面
	 * @return
	 */
	@RequestMapping(value="/toSave.html", method = RequestMethod.GET)
	public String saveOrUpdata(){
		return "/turnover/save";
	}
	
	/**
	 * 保存／修改业务逻辑
	 * @param session
	 * @param request
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="/save.html", method = RequestMethod.POST)
	public String saveOrUpdata(HttpSession session, HttpServletRequest request,@ModelAttribute("parameter") Turnover parameter){
		try {
			turnoverHandler.saveOrUpdate(parameter);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	/**
	 * 判断新增或修改
	 * @param code
	 * @param model
	 * @return
	 */
	@ModelAttribute("parameter")
	private Turnover getParameter(@RequestParam(value="code", required=false)String code, Model model){
		Turnover paramter = null;
		if(null == code){
			paramter = new Turnover();
		}else{
			paramter = this.turnoverHandler.getTurnover(code);
		}
		return paramter;
	}
	
}
