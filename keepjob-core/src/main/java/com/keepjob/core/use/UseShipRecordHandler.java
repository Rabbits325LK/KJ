package com.keepjob.core.use;

import java.util.List;

import com.keepjob.core.employee.Employee;

/**
 * 船艇使用记录接口
 * @author lk
 *
 */
public interface UseShipRecordHandler {
	
	/**
	 * 创建
	 * @param record
	 * @return
	 */
	public boolean create(UseShipRecord record, Employee employee);
	
	/**
	 * 开始
	 * @param code
	 * @param employee
	 * @return
	 */
	public boolean start(String code, Employee employee);
	
	/**
	 * 结束
	 * @param code
	 * @param employee
	 * @return
	 */
	public boolean end(String code, Employee employee);
	
	/**
	 * 取消
	 * @param code
	 * @param employee
	 * @return
	 */
	public boolean cancel(String code, Employee employee);
	
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<UseShipRecord> finds();
	
	/**
	 * 获取所有使用者类型
	 * @return
	 */
	public List<UserType> findUserTypes();
	
}
