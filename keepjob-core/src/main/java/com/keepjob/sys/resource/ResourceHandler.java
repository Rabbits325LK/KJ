package com.keepjob.sys.resource;

import java.util.List;

import com.keepjob.sys.user.User;

public interface ResourceHandler {
	/**
	 * 获取用户对应资源信息
	 * @param userCode
	 * @return
	 */
	public List<Resource> findResourcesByUserCode(String userCode);

	public List<Operate> findOperatesAll();
		
	public boolean saveOrUpdate(User user, Resource record);
	
	public Resource getResource(String code);

	public boolean deleteResources(User user, List<String> codes);
	/**
	 * 获取资源
	 * @param url
	 * @return
	 */
	public List<Resource> findResources(String url);
}
