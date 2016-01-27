package com.keepjob.app.associator.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.app.associator.Associator;
import com.keepjob.app.associator.AssociatorDAO;
import com.keepjob.app.associator.AssociatorMapper;
import com.keepjob.app.associator.vo.AssociatorVO;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;

@Repository("associatorDAO")
public class AssociatorDAOImpl implements AssociatorDAO{

	@Autowired
	private AssociatorMapper associatorMapper;
	
	@Override
	public Associator getAssociator(Integer id) {
		// TODO Auto-generated method stub
		try {
			return this.associatorMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public PaginationResultSet<Associator> findAssociatorByPagination(
			AssociatorVO vo) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		PaginationResultSet<Associator> result = new PaginationResultSet<Associator>();
		if(StringUtils.isNotEmpty(vo.getUniqueCode())){
			criteria.andEqualTo("unique_code", StringUtils.trim(vo.getUniqueCode()));
		}
		if(StringUtils.isNotEmpty(vo.getIdCard())){
			criteria.andLike("id_cord", StringUtils.trim(vo.getIdCard()+"%"));
		}
		if(StringUtils.isNotEmpty(vo.getRealName())){
			criteria.andEqualTo("real_name", StringUtils.trim(vo.getRealName()));
		}
		if(StringUtils.isNotEmpty(vo.getSex())){
			criteria.andEqualTo("sex", StringUtils.trim(vo.getSex()));
		}
		if(StringUtils.isNotEmpty(vo.getStatus())){
			criteria.andEqualTo("status", StringUtils.trim(vo.getStatus()));
		}
		if(StringUtils.isNotEmpty(vo.getUsername())){
			criteria.andEqualTo("username", StringUtils.trim(vo.getUsername()));
		}
		if(StringUtils.isNotEmpty(vo.getPhone())){
			criteria.andEqualTo("phone", StringUtils.trim(vo.getPhone()));
		}
		example.setSkip(vo.getSkip());
		example.setRows(vo.getRows());
		example.setOrderByClause("create_time");
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.associatorMapper.selectByExample(example));
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
	public List<Associator> findAssociators(Associator param) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		List<Associator> result = new ArrayList<Associator>();
		if(StringUtils.isNotEmpty(param.getUniqueCode())){
			criteria.andEqualTo("unique_code", StringUtils.trim(param.getUniqueCode()));
		}
		if(StringUtils.isNotEmpty(param.getIdCard())){
			criteria.andLike("id_cord", StringUtils.trim(param.getIdCard()+"%"));
		}
		if(StringUtils.isNotEmpty(param.getRealName())){
			criteria.andEqualTo("real_name", StringUtils.trim(param.getRealName()));
		}
		if(StringUtils.isNotEmpty(param.getSex())){
			criteria.andEqualTo("sex", StringUtils.trim(param.getSex()));
		}
		if(StringUtils.isNotEmpty(param.getStatus())){
			criteria.andEqualTo("status", StringUtils.trim(param.getStatus()));
		}
		if(StringUtils.isNotEmpty(param.getUsername())){
			criteria.andEqualTo("username", StringUtils.trim(param.getUsername()));
		}
		if(StringUtils.isNotEmpty(param.getPhone())){
			criteria.andEqualTo("phone", StringUtils.trim(param.getPhone()));
		}
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				result = this.associatorMapper.selectByExample(example);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		return result;
	}

	@Override
	public boolean saveAssociator(Associator record) {
		// TODO Auto-generated method stub
		try {
			this.associatorMapper.insertSelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateAssociator(Associator record) {
		// TODO Auto-generated method stub
		try {
			this.associatorMapper.updateByPrimaryKeySelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteAssociators(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInInteger("id", ids);
		try {
			this.associatorMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean existsUserName(String userName) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("username", StringUtils.isEmpty(userName));
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean existsIdCard(String idCard) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("id_card", StringUtils.isEmpty(idCard));
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean exisisEmail(String email) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("email", StringUtils.isEmpty(email));
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Associator getAssociatorByUserName(String username) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("username", StringUtils.isEmpty(username));
		try {
			return this.associatorMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Associator getAssociatorByUniqueCode(String uniqueCode) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("unique_code", StringUtils.isEmpty(uniqueCode));
		try {
			return this.associatorMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean exisisPhone(String phone) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("phone", StringUtils.isEmpty(phone));
		try {
			Integer rows = this.associatorMapper.countByExample(example);
			if(rows > 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
