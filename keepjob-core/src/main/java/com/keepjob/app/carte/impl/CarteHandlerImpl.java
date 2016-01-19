package com.keepjob.app.carte.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepjob.app.carte.Carte;
import com.keepjob.app.carte.CarteDAO;
import com.keepjob.app.carte.CarteHandler;
import com.keepjob.app.carte.CarteType;
import com.keepjob.app.carte.vo.CarteVO;
import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.User;

@Service("carteHandler")
public class CarteHandlerImpl implements CarteHandler{

	@Autowired
	private CarteDAO carteDAO;
	
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	
	
	private LoggerDecorator logger = LoggerDecorator.getLogger(CarteHandlerImpl.class);
	private final String MODULE = "菜单模块"; 
	
	@Override
	public Carte getCarte(Integer id) {
		// TODO Auto-generated method stub
		return this.carteDAO.getCarte(id);
	}

	@Override
	public PaginationResultSet<Carte> findCartesByPagination(CarteVO vo) {
		// TODO Auto-generated method stub
		return this.carteDAO.findCarteByPagination(vo);
	}

	@Override
	public boolean saveOrUpdateCarte(User user, Carte record) {
		// TODO Auto-generated method stub
		if(NumberUtils.isEmpty(record.getId())){
			
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.CARTE_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成菜单主键失败，请联系管理员.");
			}
			record.setUniqueCode(uniqueCode);
			record.setCreater(user.getRealName());
			record.setCreaterCode(user.getUniqueCode());
			record.setCreateDate(DateUtils.getCurrentDateTime());
			if(StringUtils.isEmpty(record.getStatus()))record.setStatus(Status.DISABLE.getCode());
			record.setVersion("01");
			System.out.println("图片地址："+record.getCarteLogo());
			this.carteDAO.saveCarte(record);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "菜单新增成功：菜单信息为[" + record.toString() + "]"));
		}else{
			record.setUpdateDate(DateUtils.getCurrentDateTime());
			this.carteDAO.updateCarte(record);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "菜单修改成功：菜单信息为[" + record.toString() + "]"));
		}
		return true;
	}

	@Override
	public boolean remove(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		this.carteDAO.deleteCartes(ids);
		logger.info(Logger.deleteLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "菜单删除成功：菜单信息为[" + ids.toString() + "]"));
		return true;
	}

	@Override
	public boolean startCartes(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Carte record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Carte();
				record.setId(id);
				record.setStatus(Status.ENABLE.getCode());
				record.setUpdateDate(DateUtils.getCurrentDateTime());
				
				this.carteDAO.updateCarte(record);
			}
			logger.info(Logger.startLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "菜单启用成功：菜单信息为[" + ids.toString() + "]"));
		}
		return true;
	}

	@Override
	public boolean stopCartes(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Carte record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Carte();
				record.setId(id);
				record.setStatus(Status.DISABLE.getCode());
				record.setUpdateDate(DateUtils.getCurrentDateTime());
				
				this.carteDAO.updateCarte(record);
			}
			logger.info(Logger.stopLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "菜单停用成功：菜单信息为[" + ids.toString() + "]"));
		}
		return true;
	}

	@Override
	public List<CarteType> findCarteTypes() {
		// TODO Auto-generated method stub
		return this.carteDAO.findCarteTypes();
	}

}
