package com.keepjob.sys.resource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.resource.Operate;
import com.keepjob.sys.resource.OperateDAO;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceDAO;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.role.RoleDAO;
import com.keepjob.sys.user.User;

@Service("resourceHandler")
public class ResourceHandlerImpl implements ResourceHandler {
	@Autowired
	private ResourceDAO resourceDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private OperateDAO operateDAO;

	@Override
	public List<Resource> findResourcesByUserCode(String userCode) {
		return this.resourceDAO.findResourceByUserCode(userCode);
	}

	@Override
	public List<Operate> findOperatesAll() {
		return operateDAO.findOperatesAll();
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(User user, Resource record) {
		record.setUpdateTime(DateUtils.getCurrentDateTime());
		if (StringUtils.isEmpty(record.getCode())) {
			if (StringUtils.isEmpty(record.getParentCode())) {
				throw new ApplicationException("请选择上级资源。");
			}
			String  maxCode = resourceDAO.getMaxCodeByParentCode(record.getParentCode());
			record.setIndex(Short.valueOf(Integer.parseInt(maxCode.substring(maxCode.length()-2, maxCode.length()))+1+""));
			Integer codeNum = Integer.parseInt(maxCode.substring(maxCode.length()-2, maxCode.length()))+1;
			record.setCode(record.getParentCode()+StringUtils.patchZeroToString(codeNum.toString(), codeNum.toString().length()+1));
			record.setCreateDate(DateUtils.getCurrentDateTime());
			record.setCreator(user.getUniqueCode());
			record.setStatus(Status.ENABLE.getCode());
			this.resourceDAO.saveResource(record);
		}else{
			this.resourceDAO.updateResource(record);
		}
		return true;
	}

	@Override
	public Resource getResource(String code) {
		return resourceDAO.getResource(code);
	}

	@Override
	@Transactional
	public boolean deleteResources(User user, List<String> codes) {
		if(CollectionUtils.isNotEmpty(codes)){
			for(String code : codes){
				if(this.resourceDAO.getResourceRows(null, code) <= 0){
					this.roleDAO.deleteRoleResourceByResourceCode(code);
					this.resourceDAO.deleteResource(code);
				}
			}
		}
		
		return true;
	}

	@Override
	public List<Resource> findResources(String url) {
		return this.resourceDAO.findResources(url);
	}
}
