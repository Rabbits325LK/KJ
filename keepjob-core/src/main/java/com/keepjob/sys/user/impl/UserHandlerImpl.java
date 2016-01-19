package com.keepjob.sys.user.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.exception.RecordFoundException;
import com.keepjob.common.exception.RecordNotFoundException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.EncryptUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.resource.ResourceDAO;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserDAO;
import com.keepjob.sys.user.UserHandler;
import com.keepjob.sys.user.UserRole;
import com.keepjob.sys.user.UserStatus;
import com.keepjob.sys.user.vo.UserVO;

@Service("userHandler")
public class UserHandlerImpl implements UserHandler {
	private final String MODULE="用户管理";
	private LoggerDecorator logger = LoggerDecorator.getLogger(UserHandlerImpl.class);
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	@Autowired
	private ResourceDAO resourceDAO;
	
	
	@Override
	public User login(String loginName, String password, String ip) {		
		User result = userDAO.getUserByLoginName(loginName);
		if(null == result || StringUtils.trim(result.getStatus()).equals(UserStatus.DELETED.getCode())){
			throw new RecordNotFoundException("用户不存在.");
		}
		
		if(!StringUtils.trim(password).equals(StringUtils.trim(result.getPassword()))){
			throw new ApplicationException("登录密码不正确.");
		}
		
		if(StringUtils.trim(result.getStatus()).equals(UserStatus.DISABLE.getCode())){
			throw new ApplicationException("当前用户已停用.");
		}
		
		if(StringUtils.trim(result.getStatus()).equals(UserStatus.LOCKED.getCode())){
			throw new ApplicationException("当前用户已锁定.");
		}
		
		//加载用户资源信息
		//result.setResources(this.resourceDAO.findResourceByUserCode(StringUtils.trim(result.getUniqueCode())));
	
		return result;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(User user, User record, List<String> roleCodes) {
		if(NumberUtils.isEmpty(record.getId())){
			if(this.existsLoginName(record.getLoginCode())){
				throw new RecordFoundException("用户名["+record.getLoginCode()+"]已注册，请更换用户名.");
			}
			
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.USER_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成用户主键失败,请联系管理员.");
			}
			//System.out.println("生成的编号："+uniqueCode);
			record.setUniqueCode(uniqueCode);
			record.setCreator(user.getUniqueCode());
			record.setIsSuper("0");
			record.setPassword(EncryptUtils.encode(StringUtils.trim(record.getPassword())+"{"+StringUtils.trim(record.getLoginCode())+"}"));
			record.setStatus(UserStatus.ENABLE.getCode());
			record.setUpdateTime(DateUtils.getTimestamp());
			record.setCreateTime(DateUtils.getTimestamp());
			record.setVersion(1);
			this.userDAO.saveUser(record);
			
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增用户信息，信息为["+record.toString()+"]"));
		}else{
			if(StringUtils.isNotEmpty(record.getPassword())){
				record.setPassword(EncryptUtils.encode(StringUtils.trim(record.getPassword())+"{"+StringUtils.trim(record.getLoginCode())+"}"));
			}
			record.setUpdateTime(DateUtils.getTimestamp());
			this.userDAO.updateUser(record);
			
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "修改用户信息，信息为["+record.toString()+"]"));
		}
		
		//删除用户角色信息
		if(!NumberUtils.isEmpty(record.getId())){
			if(StringUtils.isEmpty(record.getUniqueCode())){
				User result = this.userDAO.getUser(record.getId());
				if(null != result){
					record.setUniqueCode(result.getUniqueCode());
				}
			}
			
			this.userDAO.deleteUserRoles(Arrays.asList(record.getUniqueCode()));
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "删除用户角色信息，用户编码为["+record.getUniqueCode()+"]"));
		}
		
		//新增用户角色信息
		if (CollectionUtils.isNotEmpty(roleCodes)){
			List<UserRole> roles = new ArrayList<UserRole>();
			UserRole role = null;
			for(String roleCode : roleCodes){
				role = new UserRole();
				role.setUserCode(record.getUniqueCode());
				role.setRoleCode(roleCode);
				roles.add(role);
			}
			this.userDAO.saveUserRoles(roles);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增用户角色信息，用户角色信息为["+CollectionUtils.listToString(roles)+"]"));
		}
		return true;
	}

	@Override
	public User getUser(Integer id) {
		return this.userDAO.getUser(id);
	}

	@Override
	@Transactional
	public boolean updateUser(User user, User record) {
		if(StringUtils.isNotEmpty(record.getPassword())){
			record.setPassword(EncryptUtils.encode(StringUtils.trim(record.getPassword())+"{"+StringUtils.trim(record.getLoginCode())+"}"));
		}
		record.setUpdateTime(DateUtils.getTimestamp());
		this.userDAO.updateUser(record);
		
		logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "修改用户信息，信息为["+record.toString()+"]"));
		
		return true;
	}

	@Override
	@Transactional
	public boolean startUsers(User user, List<Integer> ids) {
		User record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new User();
				record.setId(id);
				record.setStatus(UserStatus.ENABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.userDAO.updateUser(record);
			}
			
			logger.info(Logger.startLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户启用成功，启用用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean stopUsers(User user, List<Integer> ids) {
		User record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new User();
				record.setId(id);
				record.setStatus(UserStatus.DISABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.userDAO.updateUser(record);
			}
			
			logger.info(Logger.stopLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户停用成功，停用用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean lockUsers(User user, List<Integer> ids) {
		User record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new User();
				record.setId(id);
				record.setStatus(UserStatus.LOCKED.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.userDAO.updateUser(record);
			}
			
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户锁定成功，锁定用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean removeUsers(User user, List<Integer> ids) {
		User record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new User();
				record.setId(id);
				record.setStatus(UserStatus.DELETED.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.userDAO.updateUser(record);
			}
			
			logger.info(Logger.deleteLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户删除成功，删除用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	public boolean existsLoginName(String loginName) {
		return this.userDAO.existsName(loginName);
	}

	@Override
	public User getUserByLoginName(String loginName) {
		User result = userDAO.getUserByLoginName(loginName);
		if(null==result){
			throw new RecordNotFoundException("用户名不存在");
		}
		result.setResources(resourceDAO.findResourceByUserCode(result.getUniqueCode()));
		
		
		return result;
	}

	@Override
	public PaginationResultSet<User> findUsersByPagination(User user, UserVO vo) {
		//中心管理用户
		if(user.isCenterManager()){
			if(!user.isSuper()){
				vo.setCenterCode(StringUtils.trim(user.getUserUnit()));
			}			
			return this.userDAO.findUsersByCenterManagePagination(vo);
		}
		
		
		return null;
	}

	@Override
	public List<UserRole> findUserRoleByUserCode(String userCode) {
		return this.userDAO.findUserRoleByUserCode(userCode);
	}

	@Override
	public User getUserByUniqueCode(String userCode) {
		return userDAO.getUserByUserCode(userCode);
	}
}
