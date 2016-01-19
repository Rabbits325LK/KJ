package com.keepjob.tysd.store;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.sys.user.User;
import com.keepjob.tysd.store.vo.StoreVO;

public interface StoreHandler {

	public Store getStore(Integer id);
	
	public PaginationResultSet<Store> findStoreByPagination(StoreVO vo);
	
	/**
	 * 获取店铺类型
	 * @return
	 */
	public List<StoreType> findStoreTypes();
	
	/**
	 * 启用
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean startStores(User user, List<Integer> ids);
	/**
	 * 停用
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean stopStores(User user, List<Integer> ids);
	
	/**
	 * 维护
	 * @param user
	 * @param record
	 * @return
	 */
	public boolean saveOrUpdateStore(User user, Store record);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean removeStores(User user, List<Integer> ids);
}
