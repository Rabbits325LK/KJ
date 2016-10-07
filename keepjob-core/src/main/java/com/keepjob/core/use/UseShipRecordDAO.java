package com.keepjob.core.use;

import java.util.List;

public interface UseShipRecordDAO {

	UseShipRecord get(String code);
	
	List<UseShipRecord> finds();
	
	boolean insert(UseShipRecord record);
	
	boolean update(UseShipRecord record);
}
