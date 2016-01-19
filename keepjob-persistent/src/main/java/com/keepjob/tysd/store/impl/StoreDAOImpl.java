package com.keepjob.tysd.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.StringUtils;
import com.keepjob.tysd.store.Store;
import com.keepjob.tysd.store.StoreDAO;
import com.keepjob.tysd.store.StoreMapper;
import com.keepjob.tysd.store.StoreType;
import com.keepjob.tysd.store.StoreTypeMapper;
import com.keepjob.tysd.store.vo.StoreVO;

@Repository("storeDAO")
public class StoreDAOImpl implements StoreDAO{

	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private StoreTypeMapper storeTypeMapper;
	
	public Store getStore(Integer id) {
		// TODO Auto-generated method stub
		try {
			return storeMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	public PaginationResultSet<Store> findStoreByPagination(StoreVO vo) {
		PaginationResultSet<Store> result = new PaginationResultSet<Store>();
		
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(vo.getName())){
			criteria.andLike("name", "%"+StringUtils.trimToEmpty(vo.getName())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getUniqueCode())){
			criteria.andEqualTo("unique_code", StringUtils.trimToEmpty(vo.getUniqueCode()));
		}
		if(StringUtils.isNotEmpty(vo.getStatus())){
			criteria.andEqualTo("status", StringUtils.trimToEmpty(vo.getStatus()));
		}
		if(StringUtils.isNotEmpty(vo.getType())){
			criteria.andEqualTo("type", StringUtils.trimToEmpty(vo.getType()));
		}
		example.setOrderByClause("create_date desc");
		example.setSkip(vo.getSkip());
		example.setRows(vo.getRows());
		try {
			Integer rows = this.storeMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.storeMapper.selectByExampleWithBLOBs(example));
			}
			result.setTotal(rows.longValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		return result;
	}
	
	public List<StoreType> findStoreTypes(){
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			return this.storeTypeMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateStore(Store record) {
		// TODO Auto-generated method stub
		try {
			this.storeMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
	}

	@Override
	public boolean saveStore(Store record) {
		// TODO Auto-generated method stub
		try {
			this.storeMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteStores(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInInteger("id", ids);
		try{			
			this.storeMapper.deleteByExample(example);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}		
	}
}
