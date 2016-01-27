package com.keepjob.app.associator.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.app.associator.Associator;
import com.keepjob.app.associator.AssociatorDAO;
import com.keepjob.app.associator.AssociatorHandler;
import com.keepjob.app.associator.vo.AssociatorVO;
import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.exception.RecordFoundException;
import com.keepjob.common.exception.RecordNotFoundException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.EncryptUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.User;

@Service("associatorHandler")
public class AssociatorHandlerImpl implements AssociatorHandler{

	private final String MODULE="APP会员管理";
	private final String APPCODE="移动设备";
	private final String APPIP="移动设备";
	private LoggerDecorator logger = LoggerDecorator.getLogger(AssociatorHandlerImpl.class);
	
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	@Autowired
	private AssociatorDAO associatorDAO;
	
	@Override
	public Associator login(String username, String password, String ip) {
		// TODO Auto-generated method stub
		Associator result = associatorDAO.getAssociatorByUserName(username);
		if(null == result || StringUtils.trim(result.getStatus()).equals(Status.DISABLE.getCode())){
			throw new RecordNotFoundException("用户不存在");
		}
		if(!StringUtils.trim(password).equals(StringUtils.trim(result.getPassword()))){
			throw new ApplicationException("登录密码不正确.");
		}
		
		if(StringUtils.trim(result.getStatus()).equals(Status.DISABLE.getCode())){
			throw new ApplicationException("当前用户已停用.");
		}
		
		return result;
	}

	@Override
	public Associator getAssociatorByUnqiueCode(String uniqueCode) {
		// TODO Auto-generated method stub
		return this.associatorDAO.getAssociatorByUniqueCode(uniqueCode);
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(User user, Associator record) {
		// TODO Auto-generated method stub
		if(NumberUtils.isEmpty(record.getId())){
			if(!this.existsUserName(record.getUsername())){
				throw new RecordFoundException("用户名["+record.getUsername()+"]已注册，请更换用户名.");
			}
			if(!this.existsEmail(record.getEmail())){
				throw new RecordFoundException("用户邮箱地址["+record.getUsername()+"]已注册，请更换用户邮箱地址.");
			}
			if(!this.existsPhone(record.getPhone())){
				throw new RecordFoundException("用户手机号码["+record.getUsername()+"]已注册，请更换用户手机号码.");
			}
			if(!this.existsIdCard(record.getIdCard())){
				throw new RecordFoundException("用户证件号码["+record.getUsername()+"]已注册，请更换用户证件号码.");
			}
			
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.APP_ASSOCIATOR_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成用户主键失败,请联系管理员.");
			}
			record.setUniqueCode(uniqueCode);
			record.setStatus(Status.ENABLE.getCode());
			record.setPassword(EncryptUtils.encode(StringUtils.trim(record.getPassword())+"{"+StringUtils.trim(record.getUsername())+"}"));
			record.setCreateTime(DateUtils.getCurrentDateTime());
			record.setVersion(1);
			
			this.associatorDAO.saveAssociator(record);
			if(null != user){
				logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增用户信息，信息为["+record.toString()+"]"));
			}else{
				logger.info(Logger.saveLogger(APPCODE,APPIP, MODULE, "新增用户信息，信息为["+record.toString()+"]"));
			}
		}else{
			if(StringUtils.isNotEmpty(record.getPassword())){
				record.setPassword(EncryptUtils.encode(StringUtils.trim(record.getPassword())+"{"+StringUtils.trim(record.getUsername())+"}"));
			}
			record.setUpdateTime(DateUtils.getCurrentDateTime());
			this.associatorDAO.updateAssociator(record);
			if(null != user){
				logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增用户信息，信息为["+record.toString()+"]"));
			}else{
				logger.info(Logger.saveLogger(APPCODE,APPIP, MODULE, "新增用户信息，信息为["+record.toString()+"]"));
			}
		}
		return true;
	}

	@Override
	public boolean existsUserName(String username) {
		// TODO Auto-generated method stub
		return this.associatorDAO.existsUserName(username);
	}

	@Override
	public boolean existsIdCard(String idCard) {
		// TODO Auto-generated method stub
		return this.associatorDAO.existsIdCard(idCard);
	}

	@Override
	public boolean existsPhone(String phone) {
		// TODO Auto-generated method stub
		return this.associatorDAO.exisisPhone(phone);
	}

	@Override
	public Associator getAssociator(Integer id) {
		// TODO Auto-generated method stub
		return this.associatorDAO.getAssociator(id);
	}

	@Override
	@Transactional
	public boolean deleteAssociators(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		this.associatorDAO.deleteAssociators(ids);
		logger.info(Logger.deleteLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户删除成功，删除用户编码为["+ids.toString()+"]"));
		return true;
	}

	@Override
	@Transactional
	public boolean startAssociators(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Associator record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Associator();
				record.setId(id);
				record.setStatus(Status.ENABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.associatorDAO.updateAssociator(record);
			}
			logger.info(Logger.startLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户启动成功，锁定用户编码为["+ids.toString()+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean lockAssociators(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Associator record = null;
		System.out.println("IDS:"+ids.toString());
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Associator();
				System.out.println("ID:"+id);
				record.setId(id);
				record.setStatus(Status.DISABLE.getCode());
				record.setUpdateTime(DateUtils.getCurrentDateTime());
				
				this.associatorDAO.updateAssociator(record);
			}
			logger.info(Logger.stopLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "用户锁定成功，锁定用户编码为["+ids.toString()+"]"));
		}
		return true;
	}

	@Override
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		return this.associatorDAO.exisisEmail(email);
	}

	@Override
	public PaginationResultSet<Associator> findAssociatorByPagination(
			AssociatorVO vo) {
		// TODO Auto-generated method stub
		return this.associatorDAO.findAssociatorByPagination(vo);
	}

}
