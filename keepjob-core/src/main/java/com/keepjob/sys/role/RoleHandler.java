package com.keepjob.sys.role;

import java.util.List;

import com.keepjob.sys.user.User;

public interface RoleHandler {
	/**
	 * 维护角色
	 * @param user 当前用户
	 * @param record 角色
	 * @param resourceCodes 角色对应资源编码
	 * @return
	 */
	public boolean saveOrUpdate(User user, Role record, List<String> resourceCodes);
	/**
	 * 获取角色
	 * @param id
	 * @return
	 */
	public Role getRole(Integer id);
	/**
	 * 启用角色
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean startRoles(User user, List<Integer> ids);
	/**
	 * 停用角色
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean stopRoles(User user, List<Integer> ids);
	/**
	 * 获取用户对应角色信息
	 * @param userCode
	 * @return
	 */
	public List<Role> findRolesByUserCode(String userCode);
	/**
	 * 获取所有有效角色
	 * @return
	 */
	public List<Role> findEnableRoles();
	/**
	 * 删除角色
	 * @param ids
	 * @return
	 */
	public boolean deleteRoles(User user, List<Integer> ids);
	/**
	 * 删除角色对应资源
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean deleteRoleResources(User user, List<Integer> ids);
	/**
	 * 查询总数据条数
	 * @param role
	 * @return
	 */
	public boolean existsName(Integer id, String name);
	/**
	 * 通过角色编号获取对应的资源信息
	 * @param roleCode
	 * @return
	 */
	public List<RoleResource> findRoleByResources(String roleCode);
	/**
	 * 替换角色对应资源信息
	 * @param id
	 * @param resourceCodes
	 * @return
	 */
	public boolean replaceResources(User user, Role record, List<String> resourceCodes);
}
