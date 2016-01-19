package com.keepjob.sys.user;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.sys.user.vo.UserVO;

public interface UserDAO {
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	public boolean deleteUsers(List<Integer> ids);
	/**
	 * 保存用户
	 * @param record
	 * @return
	 */
	public boolean saveUser(User record);
	/**
	 *  获取用户
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
    /**
     * 通过登录名获取
     * @param loginName
     * @return
     */
	public User getUserByLoginName(String loginName);
	/**
	 * 修改用户
	 * @param record
	 * @return
	 */
	public boolean updateUser(User record);
	/**
	 * 新增用户所属角色
	 * @param roles
	 * @return
	 */
	public boolean saveUserRoles(List<UserRole> roles);
	/**
	 * 删除用户所属角色
	 * @param userCodes
	 * @return
	 */
	public boolean deleteUserRoles(List<String> userCodes);
	/**
	 * 登录用户名是否存在
	 * @param loginName
	 * @return
	 */
	public boolean existsName(String loginName);   
	/**
	 * 判断专家是否存在用户信息
	 * @param mavinCode
	 * @return
	 */
	public boolean existsMavinUser(String mavinCode);
	
	/**
	 * 判断是否存在行政机构用户
	 * @param organCode
	 * @return
	 */
	public boolean existsOrganUser(String organCode);
	/**
	 * 通过中心管理用户查询机构信息
	 * @param vo
	 * @return
	 */
	public PaginationResultSet<User> findUsersByCenterManagePagination(UserVO vo);
	
	/**
	 * 通过行政管理用户查询机构信息
	 * @param vo
	 * @return
	 */
	public PaginationResultSet<User> findUsersByAdminManagePagination(UserVO vo);
	
	public List<UserRole> findUserRoleByUserCode(String userCode);
	
	/**根据编号获得*/
	public User getUserByUserCode(String userCode);
}
