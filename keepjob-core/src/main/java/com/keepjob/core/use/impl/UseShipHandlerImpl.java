package com.keepjob.core.use.impl;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.util.StringUtils;
import com.keepjob.core.employee.Employee;
import com.keepjob.core.use.*;
import com.keepjob.core.use.vo.UseShipRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("useShipHandler")
public class UseShipHandlerImpl implements UseShipRecordHandler {

	@Autowired
	private UseShipRecordDAO useShipRecordDAO;
	@Autowired
	private UserTypeDAO userTypeDAO;
	
	@Override
	public boolean create(UseShipRecord record, Employee employee) {
		// TODO Auto-generated method stub
		record.create(employee);
		
		return this.useShipRecordDAO.insert(record);
	}

	@Override
	public boolean start(String code, Employee employee) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		UseShipRecord record = this.useShipRecordDAO.get(StringUtils.trimToEmpty(code));
		if(null == record) {
			throw new ApplicationException("记录不存在");
		}
		if(!record.getEmployeeCode().equals(StringUtils.trimToEmpty(employee.getCode()))){
			throw new ApplicationException("你无权进行操作");
		}
		record.implement();
		return this.useShipRecordDAO.update(record);
	}

	@Override
	public boolean end(String code, Employee employee) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		UseShipRecord record = this.useShipRecordDAO.get(StringUtils.trimToEmpty(code));
		if(null == record) {
			throw new ApplicationException("记录不存在");
		}
		if(!record.getEmployeeCode().equals(StringUtils.trimToEmpty(employee.getCode()))){
			throw new ApplicationException("你无权进行操作");
		}
		record.complete();
		return this.useShipRecordDAO.update(record);
	}

	@Override
	public boolean cancel(String code, Employee employee) {
		// TODO Auto-generated method stub
		Assert.notNull(code, "唯一编码为空");
		UseShipRecord record = this.useShipRecordDAO.get(StringUtils.trimToEmpty(code));
		if(null == record) {
			throw new ApplicationException("记录不存在");
		}
		if(!record.getCreator().equals(StringUtils.trimToEmpty(employee.getCode()))){
			throw new ApplicationException("你无权进行操作");
		}
		if(record.getStatus().equals(UseStatus.COMPLETE.getCode())){
			throw new ApplicationException("当前记录已完成，不能进行取消操作");
		}
		record.cancel();
		return this.useShipRecordDAO.update(record);
	}

	@Override
	public List<UseShipRecord> finds() {
		// TODO Auto-generated method stub
		return this.useShipRecordDAO.finds(null);
	}

	@Override
	public List<UserType> findUserTypes() {
		// TODO Auto-generated method stub
		return this.userTypeDAO.finds();
	}

	@Override
	public UseShipRecord getInUseShipRecordByEmployeeCode(String employeeCode) {
		UseShipRecordVO vo = new UseShipRecordVO();
		vo.setEmployeeCode(StringUtils.trimToEmpty(employeeCode));
		vo.setStatus(UseStatus.IMPLEMENT.getCode());
		return this.useShipRecordDAO.finds(vo).get(0);
	}

}
