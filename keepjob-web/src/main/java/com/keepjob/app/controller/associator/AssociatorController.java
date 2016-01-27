package com.keepjob.app.controller.associator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.app.associator.Associator;
import com.keepjob.app.associator.AssociatorHandler;
import com.keepjob.app.associator.vo.AssociatorVO;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.user.User;

@Controller
@RequestMapping("/app/associator")
public class AssociatorController {

	@Autowired
	private AssociatorHandler associatorHandler;
	@Autowired
	private Gson gson;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request) {
		return "app/associator/associatorView";
	}

	@RequestMapping(value = "/addAssociator.html", method = RequestMethod.GET)
	public String toAddAssociatorView(HttpSession session, HttpServletRequest request) {
		return "app/associator/associatorAdd";
	}

	@RequestMapping(value = "/editAssociator.html", method = RequestMethod.GET)
	public String toEditAssociatorView(HttpSession session, HttpServletRequest request,
			@ModelAttribute("parameter") Associator parameter) {
		request.setAttribute("result", parameter);
		return "app/associator/associatorEdit";
	}

	@RequestMapping(value = "/searchAssociator.json", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchAssociator(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "uniqueCode", required = false) String uniqueCode,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "idCard", required = false) String idCard,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows) {
		AssociatorVO vo = new AssociatorVO();
		vo.setUniqueCode(uniqueCode);
		vo.setUsername(username);
		vo.setRealName(realName);
		vo.setIdCard(idCard);
		vo.setPhone(phone);
		vo.setSex(sex);
		vo.setStatus(status);
		vo.setRows(rows);
		vo.setPage(page);
		vo.setSort(sort);
		vo.setOrder(order);
		PaginationResultSet<Associator> result = this.associatorHandler.findAssociatorByPagination(vo);
		return gson.toJson(result);
	}

	@RequestMapping(value = "/startAssociator.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String startAssociator(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) List<Integer> ids) {
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.associatorHandler.startAssociators(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}

	@RequestMapping(value = "/lockAssociator.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String lockAssociator(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) List<Integer> ids) {
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.associatorHandler.lockAssociators(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}

	@RequestMapping(value = "/removeAssociator.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String removeAssociator(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) List<Integer> ids) {
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.associatorHandler.deleteAssociators(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}

	@RequestMapping(value = "/saveAssociator.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String saveAssociator(HttpSession session, HttpServletRequest request,
			@ModelAttribute("parameter") Associator parameter) {
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.associatorHandler.saveOrUpdate(user, parameter);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}

	@RequestMapping(value = "/checkUserName.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String checkUserName(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username) {
		if (this.associatorHandler.existsUserName(username)) {
			return gson.toJson(JSONMessage.createSuccessMessage());
		} else {
			return gson.toJson(JSONMessage.createFailedMessage("登录名已存在,请更换登录名称."));
		}
	}

	@RequestMapping(value = "/checkPhone.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String checkPhone(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "phone", required = false) String phone) {
		return this.associatorHandler.existsPhone(phone) == true ? gson.toJson(JSONMessage.createSuccessMessage())
				: gson.toJson(JSONMessage.createFailedMessage("该号码已注册,请更换号码."));
	}

	@RequestMapping(value = "/checkEmail.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String checkEmail(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "email", required = false) String email){
		return this.associatorHandler.existsEmail(email) == true ? 
				gson.toJson(JSONMessage.createSuccessMessage())
				: gson.toJson(JSONMessage.createFailedMessage("该邮箱地址已注册,请更换邮箱地址."));
	}
	
	@ModelAttribute("parameter")
	private Associator getParameter(@RequestParam(value = "id", required = false) Integer id) {
		Associator parameter = null;
		if (null == id) {
			parameter = new Associator();
		} else {
			parameter = this.associatorHandler.getAssociator(id);
		}
		return parameter;
	}
}
