package com.keepjob.app.associator;

import java.util.List;

import com.keepjob.app.associator.vo.AssociatorVO;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.sys.user.User;

public interface AssociatorHandler {

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param ip
	 * @return
	 */
	public Associator login(String username, String password, String ip);
	
	/**
	 * 根据唯一编号获取
	 * @param uniqueCode
	 * @return
	 */
	public Associator getAssociatorByUnqiueCode(String uniqueCode);
	
	/**
	 * 维护
	 * @param record
	 * @return
	 */
	public boolean saveOrUpdate(User user, Associator record);
	
	/**
	 * 验证账号
	 * @param username
	 * @return
	 */
	public boolean existsUserName(String username);
	/**
	 * 验证会员身份证号
	 * @param id
	 * @param cardCode
	 * @return
	 */
	public boolean existsIdCard(String idCard);
	/**
	 * 验证手机号码唯一
	 * @param id
	 * @param phone
	 * @return
	 */
	public boolean existsPhone(String phone);
	
	/**
	 * 验证邮箱地址
	 * @param id
	 * @param email
	 * @return
	 */
	public boolean existsEmail(String email);
	/**
	 * 获取会员信息
	 * @param id
	 * @return
	 */
	public Associator getAssociator(Integer id);
	
	/**
	 * 删除会员信息
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean deleteAssociators(User user, List<Integer> ids);
	
	/**
	 * 启动会员信息
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean startAssociators(User user, List<Integer> ids);
	
	/**
	 * 锁定会员信息
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean lockAssociators(User user, List<Integer> ids);
	
	public PaginationResultSet<Associator> findAssociatorByPagination(AssociatorVO vo);
}
