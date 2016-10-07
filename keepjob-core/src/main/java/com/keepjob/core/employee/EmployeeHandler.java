package com.keepjob.core.employee;

import java.util.List;

/**
 * 员工接口类
 * @author lk
 *
 */
public interface EmployeeHandler {
	
	/**
	 * 绑定微信
	 * @param wechatId
	 * @return
	 */
	public boolean bindWeChat(String wechatId, String wechatName);
	
	/**
	 * 验证是否已绑定
	 * @param wechatId
	 * @return
	 */
	public boolean existWeChat(String wechatId);
	
	/**
	 * 根据员工类型获取员工列表
	 * @param category
	 * @return
	 */
	public List<Employee> findsByCategory(String category);
	
	/**
	 * 获取员工类型
	 * @return
	 */
	public List<EmployeeCategory> findEmployeeCategorys();
}
