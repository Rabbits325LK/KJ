package com.keepjob.core.webmodel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepjob.common.enums.WebModel;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.core.members.Members;
import com.keepjob.core.webmodel.WebModelA;
import com.keepjob.core.webmodel.WebModelADAO;
import com.keepjob.core.webmodel.WebModelAHandler;
import com.keepjob.core.webmodel.vo.WebModelAVO;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.impl.UserHandlerImpl;

@Service("webModelAHandler")
public class WebModelAHandlerImpl implements WebModelAHandler {

	private final String MODULE="用户管理";
	private LoggerDecorator logger = LoggerDecorator.getLogger(UserHandlerImpl.class);
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	@Autowired
	private WebModelADAO webModelADAO;
	
	@Override
	public WebModelA getWebModelA(Integer id) {
		// TODO Auto-generated method stub
		return this.getWebModelA(id);
	}

	@Override
	public PaginationResultSet<WebModelA> findWebModelAPagination(WebModelAVO vo) {
		// TODO Auto-generated method stub
		return this.webModelADAO.findWebModelAByPagination(vo);
	}

	@Override
	public boolean saveOrUpdateWebModelA(Members members, WebModelA record) {
		// TODO Auto-generated method stub
		if(NumberUtils.isEmpty(record.getId())){
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.KEEPJOB_WEB_MODEL_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成模版主键失败,请联系管理员.");
			}
			record.setUniqueCode(uniqueCode);
			record.setCreaterCode(StringUtils.trimToEmpty(members.getUniqueCode()));
			record.setCreateDate(DateUtils.getCurrentDateTime());
			record.setStatus(WebModel.TEMEPORARY.getCode());
			this.webModelADAO.saveWebModelA(record);
			logger.info(Logger.saveLogger(members.getUniqueCode(), members.getLastLoginIp(), MODULE , "新增模块信息，信息为["+record.toString()+"]"));
		}else{
			record.setUpdateDate(DateUtils.getCurrentDateTime());
			this.webModelADAO.updateWebModelA(record);
			logger.info(Logger.updateLogger(members.getUniqueCode(), members.getLastLoginIp(), MODULE , "修改模块信息，信息为["+record.toString()+"]"));
		}
		return true;
	}

	@Override
	public boolean deleteWebModelAs(Members members, List<Integer> ids) {
		// TODO Auto-generated method stub
		this.webModelADAO.deleteWebModelAs(ids);
		logger.info(Logger.deleteLogger(members.getUniqueCode(), members.getLastLoginIp(), MODULE , "删除模块信息，信息为["+ids.toString()+"]"));
		return true;
	}

}
