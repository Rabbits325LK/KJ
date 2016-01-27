package com.keepjob.core.webmodel.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.webmodel.WebModelA;
import com.keepjob.core.webmodel.WebModelADAO;
import com.keepjob.core.webmodel.WebModelAMapper;
import com.keepjob.core.webmodel.vo.WebModelAVO;

@Repository("webModelDAO")
public class WebModelADAOImpl implements WebModelADAO {

	@Autowired
	private WebModelAMapper webModelAMapper;
	
	@Override
	public WebModelA getWebModelA(Integer id) {
		// TODO Auto-generated method stub
		try {
			return this.webModelAMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public PaginationResultSet<WebModelA> findWebModelAByPagination(
			WebModelAVO vo) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		PaginationResultSet<WebModelA> result = new PaginationResultSet<WebModelA>();
		if(StringUtils.isNotEmpty(vo.getCreaterCode())){
			criteria.andEqualTo("create_code", StringUtils.trimToEmpty(vo.getCreaterCode()));
		}
		if(StringUtils.isNotEmpty(vo.getStatus())){
			criteria.andEqualTo("status", StringUtils.trimToEmpty(vo.getStatus()));
		}
		example.setOrderByClause("create_date");
		example.setRows(vo.getRows());
		example.setSkip(vo.getSkip());
		try {
			Integer rows = this.webModelAMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.webModelAMapper.selectByExample(example));
			}
			result.setTotal(rows.longValue());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean saveWebModelA(WebModelA record) {
		// TODO Auto-generated method stub
		try {
			this.webModelAMapper.insertSelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateWebModelA(WebModelA record) {
		// TODO Auto-generated method stub
		try {
			this.webModelAMapper.updateByPrimaryKeySelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteWebModelAs(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(CollectionUtils.isNotEmpty(ids)){
				this.webModelAMapper.deleteByExample(example);
			}else{
				throw new ApplicationException("");
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
