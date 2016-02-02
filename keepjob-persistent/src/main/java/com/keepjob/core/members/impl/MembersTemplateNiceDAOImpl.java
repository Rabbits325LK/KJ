package com.keepjob.core.members.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.core.members.MembersTemplateNice;
import com.keepjob.core.members.MembersTemplateNiceDAO;
import com.keepjob.core.members.MembersTemplateNiceMapper;

@Repository("membersTemplateNiceDAO")
public class MembersTemplateNiceDAOImpl implements MembersTemplateNiceDAO {

	@Autowired
	private MembersTemplateNiceMapper membersTemplateNiceMapper;
	
	@Override
	public MembersTemplateNice getMembersTemplateNice(Integer id) {
		// TODO Auto-generated method stub
		try {
			return this.membersTemplateNiceMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public Integer niceNums(String membersCode, String webTemplateCode) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("members_code", membersCode)
		.andEqualTo("web_template_code", webTemplateCode);
		try {
			return this.membersTemplateNiceMapper.countByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean saveMembersTemplateNice(MembersTemplateNice record) {
		// TODO Auto-generated method stub
		try {
			this.membersTemplateNiceMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateMembersTemplateNice(MembersTemplateNice record) {
		// TODO Auto-generated method stub
		try {
			this.membersTemplateNiceMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean deleteMembersTemplateNice(Integer id) {
		// TODO Auto-generated method stub
		try {
			this.membersTemplateNiceMapper.deleteByPrimaryKey(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
