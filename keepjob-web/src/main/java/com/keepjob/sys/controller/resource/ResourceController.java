package com.keepjob.sys.controller.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.keepjob.common.Constant;
import com.keepjob.common.TreeNode;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.role.RoleHandler;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;

@Controller
@RequestMapping("/sys/resource")
public class ResourceController {
	
	@Autowired
	private ResourceHandler resourceHandler;
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private RoleHandler roleHandler;
	@Autowired
	private Gson gson;
	
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "sys/resource/resourceView";
	}
	
	/**
	 * 跳转到资源添加页面
	 */
	@RequestMapping(value = "/addresource.html", method = RequestMethod.GET)
	public String addResource(HttpServletRequest request, Model model) {
		model.addAttribute("operates", this.resourceHandler.findOperatesAll());
		return "sys/resource/resourceAdd";
	}
	
	/**
	 * 跳转到资源添加页面
	 */
	@RequestMapping(value = "/editresource.html", method = RequestMethod.GET)
	public String editResource(HttpServletRequest request, @RequestParam(required = true, value = "code") String code, Model model) {
		model.addAttribute("resource", this.resourceHandler.getResource(code));
		model.addAttribute("operates", this.resourceHandler.findOperatesAll());
		return "sys/resource/resourceEdit";
	}
	
	@RequestMapping(value = "/combotree.json", method = {RequestMethod.GET,RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String combotree(HttpServletRequest request, HttpSession session){
		TreeNode tree = null;
		User user = (User) session.getAttribute(Constant.USER_KEY);
		List<Resource> resources =resourceHandler.findResourcesByUserCode(user.getUniqueCode());
		List<TreeNode> trees=new ArrayList<TreeNode>();
		if (!CollectionUtils.isEmpty(resources)) {
			for (Resource r : resources) {
				tree = new TreeNode();
				tree.setId(r.getCode());
				tree.setPid(r.getParentCode());
				tree.setText(r.getName());
				tree.setIconCls(r.getIconCls());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				trees.add(tree);
			}
		}
		return gson.toJson(trees);
	}
	
	/**
	 * 编辑资源
	 */
	@RequestMapping(value = "/saveresource.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String save(@ModelAttribute("parameter") Resource resource, HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute(Constant.USER_KEY);
		try {
			resourceHandler.saveOrUpdate(user, resource);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	/**
	 * 删除资源
	 */
	@RequestMapping(value = "/deleteresource.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String delete(HttpServletRequest request, HttpSession session, @RequestParam(required = true, value = "code") List<String> codes) {
		User user = (User) session.getAttribute(Constant.USER_KEY);
		try {
			resourceHandler.deleteResources(user, codes);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/loadresource.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	public String loadResource(HttpServletRequest request, HttpSession session,String parentCode){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		List<Resource> result = new ArrayList<Resource>();
		User currentUser = (User) session.getAttribute(Constant.USER_KEY);
		List<Resource> resources=resourceHandler.findResourcesByUserCode(currentUser.getUniqueCode());

		for(Resource resource : resources){
			if(CommonUtils.isEmpty(parentCode)){
				if(!CommonUtils.isEmpty(resource.getParentCode())){
					continue;
				}
			}else if (!parentCode.equals(resource.getParentCode())) {
				continue;
			}
			findChildren(resource, resources);
			result.add(resource);
		}
		return gson.toJson(result);
	}
	
	private void findChildren(Resource resource, List<Resource> resources) {
		if (CollectionUtils.isEmpty(resources)) {
			return;
		}
		List<Resource> result = new ArrayList<Resource>();
		for (Resource item : resources) {
			if (resource.getCode().equals(item.getParentCode())) {
				result.add(item);
				findChildren(item, resources);
			}
		}
		resource.setChildren(result);
	}

	@ModelAttribute("parameter")
	private Resource parameter(@RequestParam(value="code", required=false) String code, Model model){
		Resource paramter = null;
		if(StringUtils.isEmpty(code)){
			paramter = new Resource();
		}else{
			paramter = this.resourceHandler.getResource(code);
		}
		return paramter;
	}
}
