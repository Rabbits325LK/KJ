package com.keepjob.core.use;

import com.keepjob.core.use.vo.UseShipRecordVO;

import java.util.List;

public interface UseShipRecordDAO {

	UseShipRecord get(String code);
	
	List<UseShipRecord> finds(UseShipRecordVO vo);
	
	boolean insert(UseShipRecord record);
	
	boolean update(UseShipRecord record);
}
