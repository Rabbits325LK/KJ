package com.keepjob.sys.role.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.common.enums.Status;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.role.Role;
import com.keepjob.sys.role.RoleDAO;
import com.keepjob.sys.role.RoleHandler;
import com.keepjob.sys.role.RoleResource;
import com.keepjob.sys.user.User;

@Service("roleHandler")
public class RoleHandlerImpl implements RoleHandler {
	private final String MODULE = "角色管理";
	private LoggerDecorator logger = LoggerDecorator.getLogger(RoleHandlerImpl.class);
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ResourceHandler resourceHandler;
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;

	@Override
	@Transactional
	public boolean saveOrUpdate(User user, Role record, List<String> resourceCodes) {
		List<RoleResource> resources = null;
		RoleResource resource = null;
		if (NumberUtils.isEmpty(record.getId())) {
			record.setUniqueCode(this.uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.ROLE_CODE));
			record.setCreateDate(DateUtils.getTimestamp());
			record.setCreator(user.getUniqueCode());
			record.setStatus(Status.ENABLE.getCode());
			record.setVersion(1);
			this.roleDAO.saveRole(record);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "角色新增成功：角色信息为[" + record.toString() + "]"));
		} else {
			record.setUpdateTime(DateUtils.getTimestamp());
			this.roleDAO.updateRole(record);
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "角色修改成功：角色信息为[" + record.toString() + "]"));
		}

		// 设置角色资源
		if (CollectionUtils.isNotEmpty(resourceCodes)) {
			// 修改角色时，清除角色对应的资源信息
			if (!NumberUtils.isEmpty(record.getId())) {
				if (StringUtils.isEmpty(record.getUniqueCode())) {
					Role role = this.roleDAO.getRole(record.getId());
					if (null != role) {
						record.setUniqueCode(role.getUniqueCode());
					}
				}
				this.roleDAO.deleteResources(Arrays.asList(record.getUniqueCode()));
				logger.info(Logger.deleteLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "清除角色对应的资源信息：角色编码为[" + record.getUniqueCode() + "]"));
			}
			
			resources = new ArrayList<RoleResource>();
			for (String code : resourceCodes) {
				resource = new RoleResource();
				resource.setResourceCode(code);
				resource.setRoleCode(record.getUniqueCode());
				resources.add(resource);
			}
			this.roleDAO.saveResources(resources);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增角色对应的资源：资源信息为[" + CollectionUtils.listToString(resources) + "]"));
		}

		return true;
	}

	@Override
	public Role getRole(Integer id) {
		return this.roleDAO.getRole(id);
	}

	/**
	 * 批量启用
	 * 
	 * @param user
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional
	public boolean startRoles(User user, List<Integer> ids) {
		Role record = null;
		if (CollectionUtils.isNotEmpty(ids)) {
			for (Integer id : ids) {
				record = new Role();
				record.setId(id);
				record.setStatus(Status.ENABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());

				this.roleDAO.updateRole(record);
			}

			logger.info(Logger.startLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, ids));
		}
		return true;
	}

	/**
	 * 批量禁用
	 * 
	 * @param user
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional
	public boolean stopRoles(User user, List<Integer> ids) {
		Role record = null;
		if (CollectionUtils.isNotEmpty(ids)) {
			for (Integer id : ids) {
				record = new Role();
				record.setId(id);
				record.setStatus(Status.DISABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());

				this.roleDAO.updateRole(record);
			}

			logger.info(Logger.stopLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, ids));
		}
		return true;
	}

	/**
	 * 根据用户编码查询角色
	 * 
	 * @param userCode
	 * @return
	 */
	@Override
	public List<Role> findRolesByUserCode(String userCode) {
		return this.roleDAO.findRolesByUserCode(userCode);
	}
	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional
	public boolean deleteRoles(User user, List<Integer> ids) {
		//先删除角色对应资源
		this.deleteRoleResources(user, ids);
		return this.roleDAO.deleteRoles(ids);
	}


	@Override
	public List<RoleResource> findRoleByResources(String roleCode) {
		return this.roleDAO.findRoleByResources(roleCode);
	}

	@Override
	public List<Role> findEnableRoles() {
		return this.roleDAO.findEnableRoles();
	}

	@Override
	public boolean existsName(Integer id, String name) {
		return this.roleDAO.existsName(id, name);
	}

	@Override
	@Transactional
	public boolean deleteRoleResources(User user, List<Integer> ids) {
		Role role = null;
		List<String> roleCodes = new ArrayList<String>();
		
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				role = this.roleDAO.getRole(id);
				if(null != role){
					roleCodes.add(role.getUniqueCode());
				}
			}
			this.roleDAO.deleteResources(roleCodes);
		}
		return true;
	}

	@Transactional
	public boolean replaceResources(User user, Role record, List<String> resourceCodes){
		List<RoleResource> list = new ArrayList<RoleResource>();
		
		if(StringUtils.isNotEmpty(record.getUniqueCode())){ //null != record
			this.roleDAO.deleteResources(Arrays.asList(record.getUniqueCode()));
			if(CollectionUtils.isNotEmpty(resourceCodes)){
				RoleResource records = null;
				for(String code : resourceCodes){
					records = new RoleResource();
					records.setResourceCode(code);
					records.setRoleCode(record.getUniqueCode());
					//System.out.println(records.getResourceCode());
					//System.out.println(records.getRoleCode());
					list.add(records);
				}
				this.roleDAO.saveResources(list);
				logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, record.getUniqueCode()+"角色对应的资源：资源信息为[" + resourceCodes.toString() + "]"));
			}
		}
		return true;
	}
}
