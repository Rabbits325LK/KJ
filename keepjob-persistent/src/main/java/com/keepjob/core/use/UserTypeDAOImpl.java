package com.keepjob.core.use;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;

@Repository("userTypeDAO")
public class UserTypeDAOImpl implements UserTypeDAO {

	@Autowired
	private UserTypeMapper userTypeMapper;
	
	@Override
	public List<UserType> finds() {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		try {
			return this.userTypeMapper.selectByExample(example);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
