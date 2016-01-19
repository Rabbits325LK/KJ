package com.keepjob.app.carte;

import java.util.List;

import com.keepjob.app.carte.vo.CarteVO;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.sys.user.User;

public interface CarteHandler {

	public Carte getCarte(Integer id);
	
	public PaginationResultSet<Carte> findCartesByPagination(CarteVO vo);
	
	public boolean saveOrUpdateCarte(User user, Carte param);
	
	public boolean remove(User user, List<Integer> ids);
	
	public boolean startCartes(User user, List<Integer> ids);
	
	public boolean stopCartes(User user, List<Integer> ids);
	
	public List<CarteType> findCarteTypes();
}
