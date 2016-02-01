package com.keepjob.core.controller.members;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.members.Members;
import com.keepjob.core.members.MembersHandler;
import com.keepjob.core.members.vo.MembersVO;
import com.keepjob.sys.user.User;

@Controller
@RequestMapping("/core/members")
public class MembersController {

	@Autowired
	private Gson gson;
	@Autowired
	private MembersHandler membersHandler;
	
	//	进入首页
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		return "core/members/membersView";
	}

	//	进入新增页面
	@RequestMapping(value = "/addMembers.html", method = RequestMethod.GET)
	public String toAddMenbersView(HttpSession session, HttpServletRequest request){
		return "core/members/membersAdd";
	}
	
	//	进入编辑页面
	@RequestMapping(value = "/editMembers.html", method = RequestMethod.GET)
	public String toEditMembersView(HttpSession session, HttpServletRequest request,
			@ModelAttribute("parameter")Members parameter){
		request.setAttribute("result", parameter);
		return "core/members/membersEdit";
	}
	
	//	删除功能
	@RequestMapping(value = "/removeMembers.json", method = RequestMethod.POST)
	@ResponseBody
	public String removeMembers(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.membersHandler.removeMembers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	//	启用功能
	@RequestMapping(value = "/startMembers.json", method = RequestMethod.POST)
	@ResponseBody
	public String startMembers(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.membersHandler.startMembers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	//	锁定功能
	@RequestMapping(value = "/lockMembers.json", method = RequestMethod.POST)
	@ResponseBody
	public String lockMembers(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.membersHandler.lockMembers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	//	检索功能
	@RequestMapping(value = "/searchMembers.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchMembers(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "page", required = false) Integer page){
		try {
			MembersVO vo = new MembersVO();
			vo.setEmail(email);
			vo.setOrder(order);
			vo.setPage(page);
			vo.setPhone(phone);
			vo.setRealName(realName);
			vo.setRows(rows);
			vo.setSex(sex);
			vo.setSort(sort);
			vo.setStatus(status);
			PaginationResultSet<Members> result = this.membersHandler.findMembersByPagination(vo);
			return gson.toJson(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	//	密码验证
	@RequestMapping(value = "/checkpassword.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkPassword(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "searchCode", required = false) String searchCode,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "queryType", required = false) String queryType){
		try {
			if(this.membersHandler.checkPassword(queryType, searchCode, password)){
				return gson.toJson(JSONMessage.createSuccessMessage());
			}else{
				return gson.toJson(JSONMessage.createFailedMessage("密码错误"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	//	邮箱验证
	@RequestMapping(value = "/existsemail.json", method = RequestMethod.POST)
	@ResponseBody
	public String existsEmail(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "email", required = false) String email){
		try {
			if(this.membersHandler.existsEmail(email)){
				return gson.toJson(JSONMessage.createSuccessMessage());
			}else{
				 return gson.toJson(JSONMessage.createFailedMessage("邮箱地址已被使用，请更换"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	//	手机号码验证
	@RequestMapping(value = "/existsphone.json", method = RequestMethod.POST)
	@ResponseBody
	public String existsPhone(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "phone", required = false) String phone){
		try {
			if(this.membersHandler.existsPhone(phone)){
				return gson.toJson(JSONMessage.createSuccessMessage());
			}else{
				return gson.toJson(JSONMessage.createFailedMessage("手机号已被注册,请更换"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private Members getParameter(@RequestParam(value = "id", required = false) Integer id, Model model){
		Members parameter = null;
		if(null == id){
			parameter = new Members();
		}else{
			parameter = this.membersHandler.getMembers(null, null, id);
		}
		return parameter;
	}
}
