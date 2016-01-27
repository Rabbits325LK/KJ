package com.keepjob.core.controller.webmodel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.webmodel.WebModelA;
import com.keepjob.core.webmodel.WebModelAHandler;
import com.keepjob.core.webmodel.vo.WebModelAVO;

@Controller
@RequestMapping("/core/webmodela")
public class WebModelAController {

	@Autowired
	private Gson gson;
	@Autowired
	private WebModelAHandler webModelAHandler;
	
	//	进入首页
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		return "core/webModelA/webModelAView";
	}
	
	//	检索功能
	@RequestMapping(value = "/saerchWebModelA.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchWebModelA(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "createrCode", required = false) String createrCode,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "page", required = false) Integer page){
		try {
			WebModelAVO vo = new WebModelAVO();
			vo.setCreaterCode(createrCode);
			vo.setStatus(status);
			vo.setRows(rows);
			vo.setSort(sort);
			vo.setOrder(order);
			vo.setPage(page);
			PaginationResultSet<WebModelA> result = this.webModelAHandler.findWebModelAPagination(vo);
			return gson.toJson(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	

}
