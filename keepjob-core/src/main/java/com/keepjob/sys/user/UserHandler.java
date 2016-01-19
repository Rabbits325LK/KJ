package com.keepjob.sys.user;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.sys.user.vo.UserVO;

public interface UserHandler {
	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @param ip
	 * @return
	 */
	public User login(String loginName, String password, String ip);
	/**
	 * 新增用户信息和所属角色信息
	 * @param user 当前登录用户
	 * @param record 新增用户信息
	 * @param roleCodes 用户所属角色编码信息
	 * @return
	 */
	public boolean saveOrUpdate(User user, User record, List<String> roleCodes);
	/**
	 * 验证用户名是否存在
	 * @param loginName 登录用户名
	 * @return
	 */
	public boolean existsLoginName(String loginName);
	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public User getUser(Integer id);
	/**
	 * 修改
	 * @param user
	 * @param record
	 * @return
	 */
	public boolean updateUser(User user, User record);
	/**
	 * 启用
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean startUsers(User user, List<Integer> ids);
	/**
	 * 停用
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean stopUsers(User user, List<Integer> ids);
	
	/**
	 * 锁定
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean lockUsers(User user, List<Integer> ids);
	/**
	 * 删除
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean removeUsers(User user, List<Integer> ids);
	/**
	 * 根据登录号获取对应用户信息
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);
	/**
	 * 用户分页查询
	 * @param vo
	 * @return
	 */
	public PaginationResultSet<User> findUsersByPagination(User user, UserVO vo);
	
	public List<UserRole> findUserRoleByUserCode(String userCode);
	
	/**
	 * 根据编号获得
	 * @param userCode
	 * @return
	 */
	public User getUserByUniqueCode(String userCode);
}
