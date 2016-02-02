package com.keepjob.core.members.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.EncryptUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.core.members.Members;
import com.keepjob.core.members.MembersDAO;
import com.keepjob.core.members.MembersHandler;
import com.keepjob.core.members.vo.MembersVO;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserStatus;
import com.keepjob.sys.user.impl.UserHandlerImpl;

@Service("membersHandler")
public class MembersHandlerImpl implements MembersHandler{
	private final String MODULE="用户管理";
	private LoggerDecorator logger = LoggerDecorator.getLogger(UserHandlerImpl.class);
	@Autowired
	private MembersDAO membersDAO;
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	
	@Override
	public Members getMembers(String queryType, String searchCode, Integer id) {
		// TODO Auto-generated method stub
		Members record = null;
		if(StringUtils.isNotEmpty(queryType)){
			if(queryType.equals("email")){
				record = this.membersDAO.getMembersByEmail(StringUtils.trim(searchCode));
			}else if(queryType.equals("phone")){
				record = this.membersDAO.getMembersByPhone(StringUtils.trim(searchCode));
			}
		}else{
			record = this.membersDAO.getMembers(id);
		}
		return record;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(User user, Members record) {
		// TODO Auto-generated method stub
		if(NumberUtils.isEmpty(record.getId())){
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.KEEPJOB_MEMBERS_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成用户主键失败,请联系管理员.");
			}
			//record.setPassword(EncryptUtils.encode(StringUtils.trimToEmpty(record.getPassword())));
			record.setUniqueCode(uniqueCode);
			record.setStatus(UserStatus.ENABLE.getCode());
			record.setCreateDate(DateUtils.getCurrentDateTime());
			if(StringUtils.isNotEmpty(record.getPassword())){
				record.setPassword(EncryptUtils.encode(StringUtils.trimToEmpty(record.getPassword())));
			}
			this.membersDAO.saveMembers(record);
			logger.info(Logger.saveLogger(user != null ? user.getUniqueCode() : null, user != null ? user.getLastLoginIp() : null, MODULE, "新增用户信息，信息为["+record.toString()+"]"));
		}else{
			if(StringUtils.isNotEmpty(record.getPassword())){
				record.setPassword(EncryptUtils.encode(StringUtils.trimToEmpty(record.getPassword())));
			}
			record.setUpdateDate(DateUtils.getCurrentDateTime());
			this.membersDAO.updateMembers(record);
			logger.info(Logger.updateLogger(user != null ? user.getUniqueCode() : null, user != null ? user.getLastLoginIp() : null, MODULE, "修改用户信息，信息为["+record.toString()+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean removeMembers(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		this.membersDAO.deletesMembers(ids);
		logger.info(Logger.deleteLogger(user != null ? user.getUniqueCode() : null, user != null ? user.getLastLoginIp() : null, MODULE, "用户删除成功，删除用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		return true;
	}

	@Override
	@Transactional
	public boolean startMembers(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Members record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Members();
				record.setId(id);
				record.setStatus(UserStatus.ENABLE.getCode());
				record.setUpdateDate(DateUtils.getCurrentDateTime());
				
				this.membersDAO.updateMembers(record);
			}
			
			logger.info(Logger.startLogger(user != null ? user.getUniqueCode() : null, user != null ? user.getLastLoginIp() : null, MODULE, "用户启用成功，启用用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	@Transactional
	public boolean lockMembers(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Members record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Members();
				record.setId(id);
				record.setStatus(UserStatus.LOCKED.getCode());
				record.setUpdateDate(DateUtils.getCurrentDateTime());
				
				this.membersDAO.updateMembers(record);
			}
			
			logger.info(Logger.startLogger(user != null ? user.getUniqueCode() : null, user != null ? user.getLastLoginIp() : null, MODULE, "用户锁定成功，启用用户编码为["+CollectionUtils.integerToString(ids)+"]"));
		}
		return true;
	}

	@Override
	public boolean existsPhone(String phone) {
		// TODO Auto-generated method stub
		return this.membersDAO.existsPhone(phone);
	}

	@Override
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		return this.membersDAO.existsEmail(email);
	}

	@Override
	public PaginationResultSet<Members> findMembersByPagination(MembersVO vo) {
		// TODO Auto-generated method stub
		return this.membersDAO.findMembersByPagination(vo);
	}

	@Override
	public boolean checkPassword(String queryType, String searchCode, String password) {
		// TODO Auto-generated method stub
		Members record = null;
		if(queryType.equals("email")){
			record = this.membersDAO.getMembersByEmail(StringUtils.trim(searchCode));
		}else if(queryType.equals("phone")){
			record = this.membersDAO.getMembersByPhone(StringUtils.trim(searchCode));
		}
		if(record.getPassword().equals(EncryptUtils.encode(StringUtils.trimToEmpty(password)))){
			return true;
		}else{
			return false;
		}
	}

}
