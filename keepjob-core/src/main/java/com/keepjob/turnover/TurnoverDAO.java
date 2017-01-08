package com.keepjob.turnover;

import java.util.List;

public interface TurnoverDAO {

	public Turnover getTurnover(String code);
	
	public List<Turnover> findTurnover();
	
	public boolean insert(Turnover record);

	public boolean update(Turnover record);
}
