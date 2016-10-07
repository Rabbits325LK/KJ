package com.keepjob.core.ship;

import java.util.List;

public interface ShipDAO {

	Ship get(String code);
	
	List<Ship> finds();
	
	List<Ship> availableFinds();
	
	boolean insert(Ship record);
	
	boolean update(Ship record);
	
	boolean deletes(List<String> codes);
}
