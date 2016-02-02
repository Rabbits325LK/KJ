package com.keepjob.core.controller.webtemplate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.webtemplate.WebTemplate;
import com.keepjob.core.webtemplate.WebTemplateHandler;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;
import com.keepjob.sys.user.User;

@Controller
@Repository("/core/webtemplate")
public class WebTemplateController {

	@Autowired
	private Gson gson;
	
	@Autowired
	private WebTemplateHandler webTemplateHandler;
	
	/**
	 * 进入首页
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		request.setAttribute("types", com.keepjob.common.enums.WebTemplate.values());
		return "core/webTemplate/webTemplateView";
	}
	
	/**
	 * 根据添加查询
	 * @param session
	 * @param request
	 * @param createrCode
	 * @param status
	 * @param rows
	 * @param sort
	 * @param order
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/searchWebTemplate.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchWebTemplate(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "createrCode", required = false) String createrCode,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "page", required = false) Integer page){
		try {
			WebTemplateVO vo = new WebTemplateVO();
			vo.setCreaterCode(createrCode);
			vo.setStatus(status);
			vo.setRows(rows);
			vo.setSort(sort);
			vo.setOrder(order);
			vo.setPage(page);
			PaginationResultSet<WebTemplate> result = this.webTemplateHandler.findByWebTemplateByPagination(vo);
			return gson.toJson(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	/**
	 * 批量暂存
	 * @param session
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/temeporaryWebTemplates.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String temeporaryWebTemplates(HttpSession session, HttpServletRequest request, 
			@RequestParam(value = "ids", required = true) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.webTemplateHandler.temeporaryWebTemplates(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	/**
	 * 批量发布
	 * @param session
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/releaseWebTemplates.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String releaseWebTemplates(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "ids", required = true) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.webTemplateHandler.releaseWebTemplates(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	/**
	 * 批量私有
	 * @param session
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/privateWebTemplates.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String privateWebTemplates(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "ids", required = true) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.webTemplateHandler.privateWebTemplates(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
}
