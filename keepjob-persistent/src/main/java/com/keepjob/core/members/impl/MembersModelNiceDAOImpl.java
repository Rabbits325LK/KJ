package com.keepjob.core.members.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.core.members.MembersModelNice;
import com.keepjob.core.members.MembersModelNiceDAO;
import com.keepjob.core.members.MembersModelNiceMapper;

@Repository("membersModelNiceDAO")
public class MembersModelNiceDAOImpl implements MembersModelNiceDAO {

	@Autowired
	private MembersModelNiceMapper membersModelNiceMapper;
	
	@Override
	public boolean saveMembersModelNice(MembersModelNice record) {
		// TODO Auto-generated method stub
		try {
			this.membersModelNiceMapper.insertSelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateMembersModelNice(MembersModelNice record) {
		// TODO Auto-generated method stub
		try {
			this.membersModelNiceMapper.updateByPrimaryKeySelective(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteMembersModelNiceByCode(String membersCode,
			String webModelCode) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		try {
			if(StringUtils.isNotEmpty(membersCode)){
				criteria.andEqualTo("members_code", StringUtils.trimToEmpty(membersCode));
			}
			if(StringUtils.isNotEmpty(webModelCode)){
				criteria.andEqualTo("web_model_code", StringUtils.trimToEmpty(webModelCode));
			}
			this.membersModelNiceMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Integer countMembersModelNiceByCode(String membersCode,
			String webModelCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		try {
			if(StringUtils.isNotEmpty(membersCode)){
				criteria.andEqualTo("members_code", StringUtils.trimToEmpty(membersCode));
			}
			if(StringUtils.isNotEmpty(webModelCode)){
				criteria.andEqualTo("web_model_code", StringUtils.trimToEmpty(webModelCode));
			}
			return this.membersModelNiceMapper.countByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean existsMembersModelNiceByCode(String membersCode,
			String webModelCode) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		try {
			if(StringUtils.isNotEmpty(membersCode)){
				criteria.andEqualTo("members_code", StringUtils.trimToEmpty(membersCode));
			}
			if(StringUtils.isNotEmpty(webModelCode)){
				criteria.andEqualTo("web_model_code", StringUtils.trimToEmpty(webModelCode));
			}
			Integer rows = this.membersModelNiceMapper.deleteByExample(example);
			if(rows > 0){
				throw new ApplicationException("用户编号:"+membersCode != null ? membersCode : "" + ",已经对" + webModelCode != null ? webModelCode : "");
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
