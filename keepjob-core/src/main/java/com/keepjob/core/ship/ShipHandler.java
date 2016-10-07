package com.keepjob.core.ship;

import java.util.List;

public interface ShipHandler {
	
	/**
	 * 获取所有船艇
	 * @return
	 */
	List<Ship> finds();
	
	/**
	 * 使用中
	 * @param code
	 * @return
	 */
	boolean inUse(String code);
	
	/**
	 * 正常
	 * @param code
	 * @return
	 */
	boolean normal(String code);
	
	/**
	 * 获取所有正常船艇
	 * @return
	 */
	List<Ship> findNormals();
}
