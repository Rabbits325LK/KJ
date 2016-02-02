package com.keepjob.core.webtemplate.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.core.members.Members;
import com.keepjob.core.webtemplate.WebTemplate;
import com.keepjob.core.webtemplate.WebTemplateDAO;
import com.keepjob.core.webtemplate.WebTemplateHandler;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.User;

@Service("webTemplateHandler")
public class WebTemplateHandlerImpl implements WebTemplateHandler{

	private final String MODULE = "模板管理";
	
	private LoggerDecorator logger = LoggerDecorator.getLogger(WebTemplateHandlerImpl.class);
	
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	
	@Autowired
	private WebTemplateDAO webTemplateDAO;
	
	@Override
	public WebTemplate getWebTemplate(Integer id) {
		// TODO Auto-generated method stub
		return this.webTemplateDAO.getWebTemplate(id);
	}

	@Override
	public PaginationResultSet<WebTemplate> findByWebTemplateByPagination(
			WebTemplateVO vo) {
		// TODO Auto-generated method stub
		return this.webTemplateDAO.findWebTemplateByPagination(vo);
	}

	@Override
	public boolean saveOrUpdateWebTemplate(Members members, WebTemplate record) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(members, null, record);
	}

	@Override
	public boolean removeWebTemplates(Members members, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.removes(members, null, ids);
	}

	@Override
	public boolean temeporaryWebTemplates(Members members, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.temeporarys(members, null, ids);
	}

	@Override
	public boolean releaseWebTemplates(Members members, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.releases(members, null, ids);
	}

	@Override
	public boolean privateWebTemplates(Members members, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.privates(members, null, ids);
	}

	@Override
	public boolean saveOrUpdateWebTemplate(User user, WebTemplate record) {
		// TODO Auto-generated method stub
		return this.saveOrUpdate(null, user, record);
	}

	@Override
	public boolean removeWebTemplates(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.removes(null, user, ids);
	}

	@Override
	public boolean temeporaryWebTemplates(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.temeporarys(null, user, ids);
	}

	@Override
	public boolean releaseWebTemplates(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.releases(null, user, ids);
	}

	@Override
	public boolean privateWebTemplates(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.privates(null, user, ids);
	}
	
	private boolean saveOrUpdate(Members members, User user, WebTemplate record){
		if(NumberUtils.isEmpty(record.getId())){
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.KEEPJOB_WEB_MODEL_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成模版主键失败,请联系管理员.");
			}
			record.setUniqueCode(uniqueCode);
			record.setCreaterCode(StringUtils.trimToEmpty(members != null ?members.getUniqueCode() :user.getUniqueCode()));
			record.setCreateDate(DateUtils.getCurrentDateTime());
			record.setStatus(com.keepjob.common.enums.WebTemplate.TEMEPORARY.getCode());
			this.webTemplateDAO.saveWebTemplate(record);
			logger.info(Logger.saveLogger(members != null ?members.getUniqueCode() :user.getUniqueCode(), 
					members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "新增模块信息，信息为["+record.toString()+"]"));
		}else{
			record.setUpdateDate(DateUtils.getCurrentDateTime());
			this.webTemplateDAO.updateWebTemplate(record);
			logger.info(Logger.updateLogger(members != null ?members.getUniqueCode() :user.getUniqueCode(), 
					members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "修改模块信息，信息为["+record.toString()+"]"));
		}
		return true;
	}
	
	private boolean removes(Members members, User user, List<Integer> ids){
		this.webTemplateDAO.deleteWebTemplates(ids);
		logger.info(Logger.deleteLogger(members != null ?members.getUniqueCode() :user.getUniqueCode(),
				members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "删除模块信息，信息为["+ids.toString()+"]"));
		return true;
	}
	
	private boolean temeporarys(Members members, User user, List<Integer> ids){
		WebTemplate record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = this.getWebTemplate(id);
				record.setStatus(com.keepjob.common.enums.WebTemplate.TEMEPORARY.getCode());
				this.webTemplateDAO.updateWebTemplate(record);
			}
			logger.info(Logger.updateLogger(members != null ? members.getUniqueCode() : user.getUniqueCode(), 
					members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "暂存模块信息，信息为["+ids.toString()+"]"));
		}
		return true;
	}
	
	private boolean releases(Members members, User user, List<Integer> ids){
		WebTemplate record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = this.getWebTemplate(id);
				record.setStatus(com.keepjob.common.enums.WebTemplate.RELEASE.getCode());
				this.webTemplateDAO.updateWebTemplate(record);
			}
			logger.info(Logger.updateLogger(members != null ? members.getUniqueCode() : user.getUniqueCode(), 
					members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "发布模块信息，信息为["+ids.toString()+"]"));
		}
		return true;
	}
	
	private boolean privates(Members members, User user, List<Integer> ids){
		WebTemplate record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = this.getWebTemplate(id);
				record.setStatus(com.keepjob.common.enums.WebTemplate.PRIVATE.getCode());
				this.webTemplateDAO.updateWebTemplate(record);
			}
			logger.info(Logger.updateLogger(members != null ? members.getUniqueCode() : user.getUniqueCode(), 
					members != null ? members.getLastLoginIp() : user.getLastLoginIp(), MODULE , "私有模块信息，信息为["+ids.toString()+"]"));
		}
		return true;
	}

}
