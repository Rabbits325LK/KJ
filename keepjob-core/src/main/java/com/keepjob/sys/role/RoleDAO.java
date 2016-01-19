package com.keepjob.sys.role;

import java.util.List;

public interface RoleDAO {
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public boolean saveRole(Role record);
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	public boolean updateRole(Role record);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean deleteRoles(List<Integer> ids);
	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public Role getRole(Integer id);
	/**
	 * 获得启用的角色
	 * @return
	 */
	public List<Role> findEnableRoles();
	/**
	 * 新增角色资源
	 * @param records
	 * @return
	 */
	public boolean saveResources(List<RoleResource> records);
	/**
	 * 删除角色资源信息
	 * @param roleCode
	 * @return
	 */
	public boolean deleteResources(List<String> roleCodes);
	/**
	 * 判断角色名是否存在
	 * @param name
	 * @return
	 */
	public boolean existsName(Integer id, String name);
	/**
	 * 获取用户所有角色
	 * @param userCode
	 * @return
	 */
	public List<Role> findRolesByUserCode(String userCode);
	/**
	 * 获取角色对应资源
	 * @param url
	 * @return
	 */
	public List<RoleResource> findRoleResources(String url);
	/**
	 * 获取角色对应资源
	 * @param roleCode
	 * @return
	 */
	public List<RoleResource> findRoleByResources(String roleCode);
	 /**
	  * 根据资源编号 删除资源权限关系
	  * @param resourceCode
	  * @return
	  */
	public boolean deleteRoleResourceByResourceCode(String resourceCode);
}
