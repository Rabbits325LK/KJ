package com.keepjob.app.carte;

import java.util.List;

import com.keepjob.app.carte.vo.CarteVO;
import com.keepjob.common.pagination.PaginationResultSet;

public interface CarteDAO {

	public Carte getCarte(Integer id);
	
	public List<Carte> findCartes(Carte param);
	
	public boolean saveCarte(Carte record);
	
	public boolean updateCarte(Carte record);
	
	public boolean deleteCartes(List<Integer> ids);
	
	public PaginationResultSet<Carte> findCarteByPagination(CarteVO vo);
	
	public List<CarteType> findCarteTypes();
	
}
