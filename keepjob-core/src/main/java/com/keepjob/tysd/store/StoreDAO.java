package com.keepjob.tysd.store;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.tysd.store.vo.StoreVO;

public interface StoreDAO {

	public Store getStore(Integer id);
	
	public PaginationResultSet<Store> findStoreByPagination(StoreVO vo);
	
	public List<StoreType> findStoreTypes();
	
	public boolean updateStore(Store record);
	
	public boolean saveStore(Store record);
	
	public boolean deleteStores(List<Integer> ids);
}
