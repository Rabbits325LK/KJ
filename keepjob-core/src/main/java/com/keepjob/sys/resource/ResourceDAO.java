package com.keepjob.sys.resource;

import java.util.List;

public interface ResourceDAO {
	/**
	 * 获取用户对应资源信息
	 * @param userCode
	 * @return
	 */
	public List<Resource> findResourceByUserCode(String userCode);
	/**
	 * 获取角色对应资源信息
	 * @param roleCode
	 * @return
	 */
	public List<Resource> findResourceByRoleCode(String roleCode);
		
	/**
	 * 查询资源数
	 * @param resource
	 * @return
	 */
	public Integer getResourceRows(String code, String parentCode);
	
	/**
	 * 添加资源
	 * @param resource
	 * @return
	 */
	public boolean saveResource(Resource record);
	
	/**
	 * 根据主键code查询
	 * @param code
	 * @return
	 */
	public Resource getResource(String code);
	
	/**
	 * 修改资源
	 * @param resource
	 * @return
	 */
	public boolean updateResource(Resource record);
	
	/**
	 * 根据code删除资源
	 * @param code
	 */
	public boolean deleteResource(String code);
	

	/**
	 * 获取资源对应角色关系
	 * @param url
	 * @return
	 */
	public List<Resource> findResources(String url);
	
	/**
	 * 根据parentCode查询
	 * @param parentCode
	 * @return
	 */
	public List<Resource> findResourceByParentCode(String parentCode);
	
	/**
	 * 获得最大编码
	 * @param parentCode
	 * @return
	 */
	public String getMaxCodeByParentCode(String parentCode);
}
