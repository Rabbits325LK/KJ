package com.keepjob.core.members.impl;

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
import com.keepjob.core.members.Members;
import com.keepjob.core.members.MembersDAO;
import com.keepjob.core.members.MembersMapper;
import com.keepjob.core.members.vo.MembersVO;

@Repository("membersDAO")
public class MembersDAOImpl implements MembersDAO{

	@Autowired
	private MembersMapper membersMapper;
	
	@Override
	public Members getMembers(Integer id) {
		// TODO Auto-generated method stub
		try {
			return membersMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Members getMembersByPhone(String phone) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(StringUtils.isNotEmpty(phone)){
				example.createCriteria().andEqualTo("phone", StringUtils.trim(phone));
				return this.membersMapper.selectByExample(example).get(0);
			}else{
				throw new ApplicationException("电话号码为空");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Members getMembersByEmail(String email) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(StringUtils.isNotEmpty(email)){
				example.createCriteria().andEqualTo("email", StringUtils.trim(email));
				return this.membersMapper.selectByExample(example).get(0);
			}else{
				throw new ApplicationException("邮箱地址为空");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Members getMembersByUniqueCode(String uniqueCode) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(StringUtils.isNotEmpty(uniqueCode)){
				example.createCriteria().andEqualTo("uniqueCode", StringUtils.trim(uniqueCode));
				return this.membersMapper.selectByExample(example).get(0);
			}else{
				throw new ApplicationException("唯一编码为空");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public PaginationResultSet<Members> findMembersByPagination(MembersVO vo) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		PaginationResultSet<Members> result = new PaginationResultSet<Members>();
		if(StringUtils.isNotEmpty(vo.getPhone())){
			criteria.andEqualTo("phone", StringUtils.trim(vo.getPhone()));
		}
		if(StringUtils.isNotEmpty(vo.getEmail())){
			criteria.andEqualTo("email", StringUtils.trim(vo.getEmail()));
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
		
		example.setOrderByClause("create_date");
		example.setRows(vo.getRows());
		example.setSkip(vo.getSkip());
		
		try {
			Integer rows = this.membersMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.membersMapper.selectByExample(example));
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
	public boolean saveMembers(Members record) {
		// TODO Auto-generated method stub
		try {
			this.membersMapper.insertSelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateMembers(Members record) {
		// TODO Auto-generated method stub
		try {
			this.membersMapper.updateByPrimaryKeySelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deletesMembers(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(CollectionUtils.isNotEmpty(ids)){
				example.createCriteria().andInInteger("id", ids);
				this.membersMapper.deleteByExample(example);
				return true;
			}else{
				throw new ApplicationException("删除数据ID为空");	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean existsPhone(String phone) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(StringUtils.isNotEmpty(phone)){
				example.createCriteria().andEqualTo("phone", StringUtils.trim(phone));
				Integer rows = this.membersMapper.countByExample(example);
				if(rows > 0){
					return false;
				}else{
					return true;
				}
			}else{
				throw new ApplicationException("电话号码为空");	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			if(StringUtils.isNotEmpty(email)){
				example.createCriteria().andEqualTo("email", StringUtils.trim(email));
				Integer rows = this.membersMapper.countByExample(example);
				if(rows > 0){
					return false;
				}else{
					return true;
				}
			}else{
				throw new ApplicationException("邮箱地址为空");	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
