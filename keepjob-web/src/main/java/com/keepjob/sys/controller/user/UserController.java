package com.keepjob.sys.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.role.Role;
import com.keepjob.sys.role.RoleHandler;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;
import com.keepjob.sys.user.UserRole;
import com.keepjob.sys.user.vo.UserVO;

@RequestMapping("/sys/user")
@Controller
public class UserController {
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private Gson gson;
	/*@Autowired
	private CenterHandler centerHandler;
	@Autowired
	private OrganHandler organHandler;
	@Autowired
	private MavinHandler mavinHandler;*/
	@Autowired
	private RoleHandler roleHandler;
	
	//查询
	@RequestMapping(value = "/index.html", method =RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model){
		return "sys/user/userView";
	}
			
	@RequestMapping(value = "/search.json", method ={RequestMethod.GET, RequestMethod.POST},produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchCenter(HttpSession session, HttpServletRequest request, 
			@RequestParam(required=false, value="name") String name, 
			@RequestParam(required=false, value="organName") String organName,
			@RequestParam(required=false, value="userType") String userType, 
			@RequestParam(required=false, value="status") String status, 
			@RequestParam(required=false, value="sort") String sort,
			@RequestParam(required=false, value="order") String order, 
			@RequestParam(required=false, value="page") Integer page,
			@RequestParam(required=false, value="rows") Integer rows, Model model){
		User user = (User) session.getAttribute(Constant.USER_KEY);
		UserVO vo = new UserVO();
		vo.setName(name);
		vo.setOrganName(organName);
		vo.setStatus(status);
		vo.setUserType(userType);
		vo.setRows(rows);
		vo.setPage(page);
		vo.setSort(sort);
		vo.setOrder(order);
			
		PaginationResultSet<User> result = this.userHandler.findUsersByPagination(user, vo);
		return gson.toJson(result);
	}
	
	//新增
	@RequestMapping(value = "/adduser.html", method =RequestMethod.GET)
	public String addUser(HttpServletRequest request, HttpServletResponse response, Model model){
		List<String> roles = new ArrayList<String>();
		List<Role> list = this.roleHandler.findEnableRoles();
		if(CollectionUtils.isNotEmpty(list)){
			for(Role item : list){
				roles.add("<input type=\"checkbox\" id=\"roleCode"+StringUtils.trim(item.getUniqueCode())+"\" name=\"roleCode\" value=\""+StringUtils.trim(item.getUniqueCode())+"\"/><label style=\"display: inline; font-size:12px;\" for=\"roleCode"+StringUtils.trim(item.getUniqueCode())+"\" >"+StringUtils.trim(item.getName())+"</label>");
			}
		}
		
		model.addAttribute("roles", roles);
		return "sys/user/userAdd";
	}
	
	//编辑
	@RequestMapping(value = "/edituser.html", method =RequestMethod.GET)
	public String editUser(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute("parameter") User parameter){
		boolean mark = false;
		List<String> roles = new ArrayList<String>();
		List<Role> list = this.roleHandler.findEnableRoles();
		List<UserRole> userRoles = this.userHandler.findUserRoleByUserCode(parameter.getUniqueCode());
		if(CollectionUtils.isNotEmpty(list)){
			for(Role item : list){
				if(CollectionUtils.isNotEmpty(userRoles)){
					for(UserRole userRole : userRoles){
						if(StringUtils.trim(userRole.getRoleCode()).equals(StringUtils.trim(item.getUniqueCode()))){
							mark = true;
							break;
						}
					}
				}
				roles.add("<input type=\"checkbox\" id=\"roleCode"+StringUtils.trim(item.getUniqueCode())+"\" name=\"roleCode\" value=\""+StringUtils.trim(item.getUniqueCode())+"\""+(mark?"checked":"")+" /><label style=\"display: inline; font-size:12px;\" for=\"roleCode"+StringUtils.trim(item.getUniqueCode())+"\" >"+StringUtils.trim(item.getName())+"</label>");
				mark = false;
			}
		}
		model.addAttribute("result", parameter);
		model.addAttribute("roles", roles);
		return "sys/user/userEdit";
	}
	
	//密码重置
	@RequestMapping(value = "/repassword.html", method =RequestMethod.GET)
	public String repasswordUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true, value="id") Integer id, Model model){
		User result = this.userHandler.getUser(id);
		model.addAttribute("result", result);
		return "sys/user/repassword";
	}
	
	@RequestMapping(value = "/repassword.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String repassword(@ModelAttribute("parameter") User parameter, HttpServletRequest request, HttpSession session, Model model) {	
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.updateUser(user, parameter);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	//启用
	@RequestMapping(value = "/startuser.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String startUser(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="ids") List<Integer> ids, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.startUsers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	//停用
	@RequestMapping(value = "/stopuser.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String stopUser(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="ids") List<Integer> ids, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.stopUsers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	//锁定
	@RequestMapping(value = "/lockuser.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String lockUser(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="ids") List<Integer> ids, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.lockUsers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	//逻辑删除
	@RequestMapping(value = "/removeuser.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String removeUser(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="ids") List<Integer> ids, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.removeUsers(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	//共用
	/**
	 * 检查登录名
	 * @param request
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/checkLoginName.json", method =RequestMethod.GET, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String checkName(HttpServletRequest request, @RequestParam(required=false, value="name") String name, Model model){
		boolean mark = this.userHandler.existsLoginName(name);
		if(mark){
			return gson.toJson(JSONMessage.createFailedMessage("登录名已存在,请更换登录名称."));
		}else{
			return gson.toJson(JSONMessage.createSuccessMessage());
		}
	}
	/**
	 * 获取用户单位
	 * @param request
	 * @param userType
	 * @param searchCode
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/getUserUnits.json", method =RequestMethod.GET, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String getUserUnits(HttpServletRequest request, @RequestParam(required=false, value="userType") String userType,
			@RequestParam(required=false, value="searchCode") String searchCode, Model model){
		try{
			if(StringUtils.trim(userType).equals(UserCategory.CENTER_MANAGE_USER.getCode()) 
					|| StringUtils.trim(userType).equals(UserCategory.MAVIN_USER.getCode())){
				List<Center> centers = this.centerHandler.findEnableCenters(searchCode);
				return gson.toJson(centers);
			}else{
				List<Organ> organs = this.organHandler.findEnableOrgans(searchCode);
				return gson.toJson(organs);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	//获取专家
	@RequestMapping(value = "/getMavins.json", method =RequestMethod.GET, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String getMavins(HttpServletRequest request, @RequestParam(required=false, value="centerCode") String centerCode, Model model){
		try{
			List<Mavin> mavins = this.mavinHandler.findEnableMavins(centerCode);
			return gson.toJson(mavins);
		}catch(Exception ex){
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}*/
	
	//保存
	@RequestMapping(value = "/save.json", method =RequestMethod.POST, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String saveAsJSON(@ModelAttribute("parameter") User parameter, @RequestParam(required=false, value="roleCode") List<String> roleCodes, HttpServletRequest request, HttpSession session, Model model) {	
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);		
			this.userHandler.saveOrUpdate(user, parameter, roleCodes);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private User getParameter(@RequestParam(value="id", required=false)Integer id, Model model){
		User paramter = null;
		if(null == id){
			paramter = new User();
		}else{
			paramter = this.userHandler.getUser(id);
		}
		return paramter;
	}
}
