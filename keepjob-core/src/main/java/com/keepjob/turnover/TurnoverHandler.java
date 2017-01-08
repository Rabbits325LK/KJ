package com.keepjob.turnover;

import java.util.List;

public interface TurnoverHandler {
	
	public Turnover getTurnover(String code);

	public List<Turnover> findTurnovers();

	public boolean saveOrUpdate(Turnover record);
}


