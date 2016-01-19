package com.keepjob.tysd.store.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.generator.UniqueCodeCategory;
import com.keepjob.sys.generator.UniqueCodeGeneratorHandler;
import com.keepjob.sys.logger.Logger;
import com.keepjob.sys.logger.LoggerDecorator;
import com.keepjob.sys.user.User;
import com.keepjob.tysd.store.Store;
import com.keepjob.tysd.store.StoreDAO;
import com.keepjob.tysd.store.StoreHandler;
import com.keepjob.tysd.store.StoreType;
import com.keepjob.tysd.store.vo.StoreVO;

@Service("storeHandler")
public class StoreHandlerImpl implements StoreHandler{
	private final String MODULE="店铺管理";
	private LoggerDecorator logger = LoggerDecorator.getLogger(StoreHandlerImpl.class);
	@Autowired
	private StoreDAO storeDAO;
	@Autowired
	private UniqueCodeGeneratorHandler uniqueCodeGeneratorHandler;
	
	public Store getStore(Integer id) {
		// TODO Auto-generated method stub
		return this.storeDAO.getStore(id);
	}

	public PaginationResultSet<Store> findStoreByPagination(StoreVO vo) {
		// TODO Auto-generated method stub
		return this.storeDAO.findStoreByPagination(vo);
	}

	@Override
	public List<StoreType> findStoreTypes() {
		// TODO Auto-generated method stub
		return this.storeDAO.findStoreTypes();
	}

	@Override
	public boolean startStores(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Store record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Store();
				record.setId(id);
				record.setStatus(Status.ENABLE.getCode());
				this.storeDAO.updateStore(record);
			}
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "启用店铺信息，信息为["+ids.toString()+"]"));
		}
		return true;
	}

	@Override
	public boolean stopStores(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		Store record = null;
		if(CollectionUtils.isNotEmpty(ids)){
			for(Integer id : ids){
				record = new Store();
				record.setId(id);
				record.setStatus(Status.DISABLE.getCode());
				this.storeDAO.updateStore(record);
			}
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "停用店铺信息，信息为["+ids.toString()+"]"));
		}
		return true;
	}

	@Override
	public boolean saveOrUpdateStore(User user, Store record) {
		// TODO Auto-generated method stub
		if(NumberUtils.isEmpty(record.getId())){
			String uniqueCode = uniqueCodeGeneratorHandler.getUniqueCode(UniqueCodeCategory.STORE_CODE);
			if(StringUtils.isBlank(uniqueCode)){
				throw new ApplicationException("生成店铺主键失败,请联系管理员.");
			}
			record.setUniqueCode(uniqueCode);
			record.setCreateDate(DateUtils.getTimestamp());
			record.setStatus(Status.ENABLE.getCode());
			this.storeDAO.saveStore(record);
			logger.info(Logger.saveLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "新增店铺信息，信息为["+record.toString()+"]"));
		}else{
			this.storeDAO.updateStore(record);
			logger.info(Logger.updateLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "修改店铺信息，信息为["+record.toString()+"]"));
		}
		return true;
	}

	@Override
	public boolean removeStores(User user, List<Integer> ids) {
		// TODO Auto-generated method stub
		this.storeDAO.deleteStores(ids);
		logger.info(Logger.deleteLogger(user.getUniqueCode(), user.getLastLoginIp(), MODULE, "删除店铺信息，信息为["+ids.toString()+"]"));
		return true;
	}

}
