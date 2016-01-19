package com.keepjob.app.carte.impl;

import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.app.carte.Carte;
import com.keepjob.app.carte.CarteDAO;
import com.keepjob.app.carte.CarteMapper;
import com.keepjob.app.carte.CarteType;
import com.keepjob.app.carte.CarteTypeMapper;
import com.keepjob.app.carte.vo.CarteVO;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.CollectionUtils;

@Repository("carteDAO")
public class CarteDAOImpl implements CarteDAO{

	@Autowired
	private CarteMapper carteMapper;
	@Autowired
	private CarteTypeMapper carteTypeMapper;
	
	@Override
	public Carte getCarte(Integer id) {
		// TODO Auto-generated method stub
		try {
			return this.carteMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public List<Carte> findCartes(Carte param) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		try {
			if(null != param){
				if(StringUtils.isNotEmpty(param.getUniqueCode())){
					criteria.andEqualTo("unique_code", StringUtils.trim(param.getUniqueCode()));
				}
				if(StringUtils.isNotEmpty(param.getCarteName()))
				{
					criteria.andLike("carte_name", "%"+StringUtils.trim(param.getCarteName())+"%");
				}
				if(StringUtils.isNotEmpty(param.getCarteType())){
					criteria.andEqualTo("carte_type", StringUtils.trim(param.getCarteType()));
				}
				if(StringUtils.isNotEmpty(param.getStatus())){
					criteria.andEqualTo("status", StringUtils.trim(param.getStatus()));
				}
				if(StringUtils.isNotEmpty(param.getCreater())){
					criteria.andEqualTo("creater", "%"+StringUtils.trim(param.getCreater()+"%"));
				}
				if(StringUtils.isNotEmpty(param.getCreaterCode())){
					criteria.andEqualTo("create_code", param.getCreaterCode());
				}
			}
			example.setOrderByClause("create_date");
			Integer rows = this.carteMapper.countByExample(example);
			List<Carte> result = null;
			if(rows > 0){
				result = this.carteMapper.selectByExample(example);
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean saveCarte(Carte record) {
		// TODO Auto-generated method stub
		try {
			this.carteMapper.insertSelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateCarte(Carte record) {
		// TODO Auto-generated method stub
		try {
			this.carteMapper.updateByPrimaryKeySelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteCartes(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		try {
			if(CollectionUtils.isNotEmpty(ids)){
				criteria.andInInteger("id", ids);
			}
			this.carteMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public PaginationResultSet<Carte> findCarteByPagination(CarteVO vo) {
		// TODO Auto-generated method stub
		PaginationResultSet<Carte> result = new PaginationResultSet<Carte>();
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(null != vo){
			if(StringUtils.isNotEmpty(vo.getUniqueCode())){
				criteria.andEqualTo("unique_code", StringUtils.trim(vo.getUniqueCode()));
			}
			if(StringUtils.isNotEmpty(vo.getCarteName()))
			{
				criteria.andLike("carte_name", "%"+StringUtils.trim(vo.getCarteName())+"%");
			}
			if(StringUtils.isNotEmpty(vo.getCarteType())){
				criteria.andEqualTo("carte_type", StringUtils.trim(vo.getCarteType()));
			}
			if(StringUtils.isNotEmpty(vo.getStatus())){
				criteria.andEqualTo("status", StringUtils.trim(vo.getStatus()));
			}
			if(StringUtils.isNotEmpty(vo.getCreater())){
				criteria.andEqualTo("creater", "%"+StringUtils.trim(vo.getCreater()+"%"));
			}
			if(StringUtils.isNotEmpty(vo.getCreaterCode())){
				criteria.andEqualTo("create_code", vo.getCreaterCode());
			}
		}
		try {
			Integer rows = this.carteMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.carteMapper.selectByExample(example));
			}
			result.setTotal(rows.longValue());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		return result;
	}

	@Override
	public List<CarteType> findCarteTypes() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			return this.carteTypeMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}
	
	
}
