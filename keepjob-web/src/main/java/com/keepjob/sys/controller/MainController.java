package com.keepjob.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.TreeNode;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.resource.Operate;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	private ResourceHandler resourceHandler;
	@Autowired
	private UserHandler userHandler;
	
	@RequestMapping("/main")
	public String indexView(HttpServletRequest request, HttpServletResponse response){
		return "main";
	}
	
	@RequestMapping("/north")
	public String northView(HttpServletRequest request, HttpServletResponse response){
		return "layout/north";
	}
	
	@RequestMapping("/west")
	public String westView(HttpServletRequest request, HttpServletResponse response){
		return "layout/west";
	}
	
	@RequestMapping("/center")
	public String centerView(HttpServletRequest request, HttpServletResponse response){
		return "layout/center";
	}
	
	@RequestMapping("/south")
	public String southView(HttpServletRequest request, HttpServletResponse response){
		return "layout/south";
	}
	
	/**
	 * 根据用户信息，加载系统左侧菜单
	 * @param request
	 * @param session
	 * @param tbSysmenu
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loadleftmenu.json", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON)
	public String loadMenuJson(HttpServletRequest request, HttpSession session){
		User currentUser = (User) session.getAttribute(Constant.USER_KEY);
		List<Operate> operates = resourceHandler.findOperatesAll();
		
		List<Resource> resources=resourceHandler.findResourcesByUserCode(currentUser.getUniqueCode());
		
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for(Resource resource : resources){
			if(resource.isTop()){
				TreeNode node = new TreeNode();
				node.setId(resource.getCode());
				node.setText(resource.getName());
				String ico = getIco(operates, resource);
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", resource.getUrl());
				attributes.put("pid", resource.getParentCode());
				attributes.put("ico", ico);
				attributes.put("sort", resource.getIndex());
				attributes.put("category", resource.getCategory());
				node.setAttributes(attributes);
				node.setIconCls(ico);
				List<TreeNode> childrens = getChildNodes(resource, resources, operates);
				if (CollectionUtils.isEmpty(childrens)) {
					node.setState("closed");
				}else{
					node.setChildren(childrens);
					node.setState("open");
				}
				treeNodes.add(node);
			}
		}
		return new Gson().toJson(treeNodes);
	}
	
	/**
	 * 获取子菜单
	 * @param tbSysmenu
	 * @param resourceList
	 * @return
	 */
	private List<TreeNode> getChildNodes(Resource resource, List<Resource> resources, List<Operate> operates){
		List<Resource> children = getChildNodes(resources, resource.getCode());
		if (CollectionUtils.isEmpty(children)) {
			return null;
		}
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (Resource child : children) {
			TreeNode node = new TreeNode();
			node.setId(child.getCode());
			node.setText(child.getName());
			String ico = getIco(operates, child);
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", child.getUrl());
			attributes.put("pid", child.getParentCode());
			attributes.put("ico", child);
			attributes.put("sort", child.getIndex());
			attributes.put("category", child.getCategory());
			node.setAttributes(attributes);
			node.setIconCls(ico);
			if (getChildRows(resources, child.getCode()) == 0) {
				node.setState("closed");
			}else{
				node.setChildren(getChildNodes(child, resources, operates));
				node.setState("open");
			}
			treeNodes.add(node);
		}
		return treeNodes;
	}
	
	private List<Resource> getChildNodes(List<Resource> resources, String parentCode){
		List<Resource> result = new ArrayList<Resource>();
		if (CollectionUtils.isEmpty(resources)) {
			return null;
		}
		for (Resource resource : resources) {
			if("1".equals(StringUtils.trimToEmpty(resource.getCategory()))){
				if (StringUtils.trimToEmpty(resource.getParentCode()).equals(StringUtils.trimToEmpty(parentCode))) {
					result.add(resource);
				}
			}
			
		}
		return result;
	}
	
	
	private int getChildRows(List<Resource> resources, String parentCode){
		if (CollectionUtils.isEmpty(resources)) {
			return 0;
		}
		int rows=0;
		for (Resource resource : resources) {
			if("1".equals(StringUtils.trimToEmpty(resource.getCategory()))){
				if (StringUtils.trimToEmpty(resource.getParentCode()).equals(StringUtils.trimToEmpty(parentCode))) {
					rows ++ ;
				}
			}
		}
		return rows;
	}
	
	private String getIco(List<Operate> operates, Resource resource){
		if (CollectionUtils.isEmpty(operates)) {
			return null;
		}
		for (Operate operate : operates) {
			if (operate.getCode().equals(resource.getOperateCode())) {
				return operate.getStyle();
			}
		}
		return null;
	}
}
