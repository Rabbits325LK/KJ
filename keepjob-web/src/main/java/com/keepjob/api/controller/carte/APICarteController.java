package com.keepjob.api.controller.carte;

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
import com.keepjob.app.carte.Carte;
import com.keepjob.app.carte.CarteHandler;
import com.keepjob.app.carte.vo.CarteVO;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.user.User;

@Controller
@RequestMapping("/api/carte")
public class APICarteController {

	@Autowired
	private CarteHandler carteHandler;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/searchCarte.json", method = {RequestMethod.POST, RequestMethod.GET}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchCartes(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "carteName", required = false) String carteName,
			@RequestParam(value = "carteType", required = false) String carteType,
			@RequestParam(value = "creater", required = false) String creater,
			@RequestParam(value = "createrCode", required = false) String createrCode,
			@RequestParam(value = "uniqueCode", required = false) String uniqueCode,
			@RequestParam(value = "status", required = false) String status){
		try {
			CarteVO vo = new CarteVO();
			vo.setCarteName(carteName);
			vo.setCarteType(carteType);
			vo.setCreater(creater);
			vo.setCreaterCode(createrCode);
			vo.setUniqueCode(uniqueCode);
			vo.setUniqueCode(uniqueCode);
			PaginationResultSet<Carte> result = this.carteHandler.findCartesByPagination(vo); 
			return gson.toJson(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/saveCarte.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String saveOrUpdateCarte(HttpSession session, HttpServletRequest request, @ModelAttribute("parameter") Carte parameter){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			System.out.println(parameter.getCarteLogo());
			this.carteHandler.saveOrUpdateCarte(user, parameter);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/startCarte.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String startCartes(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.carteHandler.startCartes(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/stopCarte.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String stopCartes(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.carteHandler.stopCartes(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	

	@RequestMapping(value = "/removeCarte.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String removeCartes(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.carteHandler.remove(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private Carte getParameter(@RequestParam(value = "id", required = false) Integer id, Model model){
		Carte parameter = null;
		if(null == id){
			parameter = new Carte();
		}else{
			parameter = this.carteHandler.getCarte(id);
		}
		return parameter;
	}
	
}
