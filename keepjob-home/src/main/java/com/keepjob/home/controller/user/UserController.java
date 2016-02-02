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
import com.keepjob.core.webtemplate.WebTemplate;
import com.keepjob.core.webtemplate.WebTemplateHandler;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Gson gson;
	@Autowired
	private MembersHandler membersHandler;
	@Autowired
	private WebTemplateHandler webTemplateHandler;
	

	
	@RequestMapping( value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request, Model model){
		Members result = (Members) session.getAttribute(Constant.MEMBERS_KEY);
		request.setAttribute("result", result);
		WebTemplateVO vo = new WebTemplateVO();
		vo.setCreaterCode(result.getUniqueCode());
		model.addAttribute("models", this.webTemplateHandler.findByWebTemplateByPagination(vo).getRows());
		return "user/index";
	}
	
	@RequestMapping( value = "/showModel.html", method = RequestMethod.GET)
	public String showModelAView(HttpSession session, HttpServletRequest request){
		return "model/a/modelView";
	}
	
	@RequestMapping (value = "/toModel.html", method = RequestMethod.GET)
	public String toModelView(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam(value = "id", required = true) Integer id){
		WebTemplate webTemplate = this.webTemplateHandler.getWebTemplate(id);
		model.addAttribute("result", webTemplate);
		return "model/a/model";
	}
	
	@RequestMapping(value = "/toAddModel.html", method = RequestMethod.GET)
	public String toAddModel(HttpSession session, HttpServletRequest request){
		return "model/a/modelAdd";
	}
	
	@RequestMapping(value = "addModel.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String addModel(HttpSession session, HttpServletRequest request,
			@ModelAttribute("parameter") WebTemplate record){
		try {
			Members members = (Members) session.getAttribute(Constant.MEMBERS_KEY);
			this.webTemplateHandler.saveOrUpdateWebTemplate(members, record);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private WebTemplate getParameter(@RequestParam(value="id", required=false)Integer id, Model model){
		WebTemplate paramter = null;
		if(null == id){
			paramter = new WebTemplate();
		}else{
			paramter = this.webTemplateHandler.getWebTemplate(id);
		}
		return paramter;
	}
	
}
