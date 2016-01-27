package com.keepjob.home.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.members.Members;
import com.keepjob.core.members.MembersHandler;
import com.keepjob.core.webmodel.ModelHandler;
import com.keepjob.core.webmodel.vo.ModelVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Gson gson;
	@Autowired
	private MembersHandler membersHandler;
	@Autowired
	private ModelHandler modelHandler;
	

	
	@RequestMapping( value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request, Model model){
		Members result = (Members) session.getAttribute(Constant.MEMBERS_KEY);
		request.setAttribute("result", result);
		ModelVO vo = new ModelVO();
		vo.setCreaterCode(result.getUniqueCode());
		model.addAttribute("models", this.modelHandler.findModelPagination(vo).getRows());
		return "user/index";
	}
	
	@RequestMapping( value = "/showModel.html", method = RequestMethod.GET)
	public String showModelAView(HttpSession session, HttpServletRequest request){
		return "model/a/modelView";
	}
	
	@RequestMapping (value = "/toModel.html", method = RequestMethod.GET)
	public String toModelView(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam(value = "id", required = true) Integer id){
		com.keepjob.core.webmodel.Model webModel = this.modelHandler.getModel(id);
		model.addAttribute("result", webModel);
		return "model/a/model";
	}
	
	@RequestMapping(value = "/toAddModel.html", method = RequestMethod.GET)
	public String toAddModel(HttpSession session, HttpServletRequest request){
		return "model/a/modelAdd";
	}
	
	@RequestMapping(value = "addModel.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String addModel(HttpSession session, HttpServletRequest request,
			@ModelAttribute("parameter") com.keepjob.core.webmodel.Model webModel){
		try {
			Members members = (Members) session.getAttribute(Constant.MEMBERS_KEY);
			this.modelHandler.saveOrUpdateWebModelA(members, webModel);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private com.keepjob.core.webmodel.Model getParameter(@RequestParam(value="id", required=false)Integer id, Model model){
		com.keepjob.core.webmodel.Model paramter = null;
		if(null == id){
			paramter = new com.keepjob.core.webmodel.Model();
		}else{
			paramter = this.modelHandler.getModel(id);
		}
		return paramter;
	}
	
}
