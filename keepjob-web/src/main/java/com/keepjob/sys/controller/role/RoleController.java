package com.keepjob.sys.controller.role;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.common.util.StringUtils;
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
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.role.Role;
import com.keepjob.sys.role.RoleHandler;
import com.keepjob.sys.role.RoleResource;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	@Autowired
	private RoleHandler roleHandler;
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private ResourceHandler resourceHandler;
	@Autowired
	private Gson gson;

	/**
	 * 进入角色管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexView(HttpServletRequest request, HttpSession session, Model model) {
		return "sys/role/roleView";
	}

	/**
	 * 进入添加窗口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addrole.html" ,method = RequestMethod.GET)
	public String addView(HttpServletRequest request, Model model) {
		return "sys/role/roleAdd";
	}

	/**
	 * 进入编辑角色窗口
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editrole.html", method = RequestMethod.GET)
	public String editView(HttpServletRequest request, @RequestParam(value="id") Integer id, Model model) {
		Role role = roleHandler.getRole(id);
		List<RoleResource> resources = null;
		List<String> ids = new ArrayList<String>();
		
		if(null != role){
			resources = this.roleHandler.findRoleByResources(role.getUniqueCode());
			if(CollectionUtils.isNotEmpty(resources)){
				for(RoleResource resource : resources){
					ids.add(resource.getResourceCode());
				}
			}
		}
		
		model.addAttribute("result", role).addAttribute("ids", gson.toJson(ids));
		return "sys/role/roleEdit";
	}

	/**
	 * 进入角色资源授权窗口
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/rolegrant.html", method = RequestMethod.GET)
	public String editResourceView(HttpServletRequest request, @RequestParam(value="id") Integer id, Model model) {
		Role role = roleHandler.getRole(id);
		List<RoleResource> resources = null;
		List<String> ids = new ArrayList<String>();
		
		if(null != role){
			resources = this.roleHandler.findRoleByResources(role.getUniqueCode());
			if(CollectionUtils.isNotEmpty(resources)){
				for(RoleResource resource : resources){
					ids.add(resource.getResourceCode());
				}
			}
		}
		
		model.addAttribute("result", role).addAttribute("ids", gson.toJson(ids));
		return "sys/role/roleGrant";
	}

	/**
	 * 
	 * @param roleCode
	 * @param resourceIds
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/grant.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String roleGrant(@ModelAttribute("parameter") Role parameter,
		@RequestParam(required = false, value = "resourceCode") List<String> resourceCodes,
		HttpSession session, HttpServletRequest request, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.roleHandler.replaceResources(user, parameter, resourceCodes);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}

	/**
	 * 添加角色
	 * 
	 * @param parameter
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/save.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String save(@ModelAttribute("parameter") Role parameter, @RequestParam("resourceCode") List<String> resourceCodes, HttpSession session, Model model) {
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.roleHandler.saveOrUpdate(user, parameter, resourceCodes);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}

	/**
	 * 验证角色名是否存在
	 * @param name
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkName.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String checkName(HttpSession session, HttpServletRequest request, @RequestParam(value="id") Integer id, @RequestParam("name") String name, Model model){
		if(StringUtils.isEmpty(name)){ //CommonUtils.isEmpty(name)
			return gson.toJson(JSONMessage.createFailedMessage("传入角色名称参数为空。"));
		}
		boolean result = this.roleHandler.existsName(id, name);
		if(result){
			return gson.toJson(JSONMessage.createFailedMessage("角色名称已存在,请更换名称。"));
		}
		return gson.toJson(JSONMessage.createSuccessMessage());
	}
	
	/**
	 * 根据编号删除角色
	 * 
	 * @param roleId
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleterole.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String delete(@RequestParam(value = "ids")List<Integer> ids, HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute(Constant.USER_KEY);
		try {
			this.roleHandler.deleteRoles(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}

	/**
	 * 异步展示所有角色列表
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loadrole.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String loadRole(HttpSession session, HttpServletRequest request, Model model){
		User user = (User) session.getAttribute(Constant.USER_KEY);
		List<Role> result = null;
		if(user.isSuper()){
			result = this.roleHandler.findEnableRoles();
		}else{
			result = this.roleHandler.findRolesByUserCode(user.getUniqueCode());
		}
		
		return gson.toJson(result);
	}
	/**
	 * 批量启用角色
	 * @param session
	 * @param request
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/startrole.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String startRole(HttpSession session, HttpServletRequest request,
			@RequestParam(required=false, value="ids") List<Integer> ids, Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.roleHandler.startRoles(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	/**
	 * 停用
	 * @param session
	 * @param request
	 * @param ids
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/stoprole.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String stopRole(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="ids") List<Integer> ids,
			Model model){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.roleHandler.stopRoles(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	@ModelAttribute("parameter")
	private Role getParameter(@RequestParam(value="id", required = false)Integer id, Model model){
		Role parameter = null;
		if(null == id){
			parameter = new Role();
		}else{
			parameter = this.roleHandler.getRole(id);
		}
		return parameter;
	}
}
