package com.keepjob.sys.user.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserCategory;
import com.keepjob.sys.user.UserDAO;
import com.keepjob.sys.user.UserMapper;
import com.keepjob.sys.user.UserRole;
import com.keepjob.sys.user.UserRoleMapper;
import com.keepjob.sys.user.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public boolean deleteUsers(List<Integer> ids) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInInteger("id", ids);
		try{			
			this.userMapper.deleteByExample(example);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}		
	}

	@Override
	public boolean saveUser(User record) {
		try{
			this.userMapper.insert(record);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public User getUser(Integer id) {
		try{
			return this.userMapper.selectByPrimaryKey(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public User getUserByLoginName(String loginName) {
		try{
			MyBatisCriteria example = new MyBatisCriteria();
			example.createCriteria().andEqualTo("login_code", loginName);
			List<User> results = this.userMapper.selectByExample(example);
			return CollectionUtils.isEmpty(results) ? null : results.get(0);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean updateUser(User record) {		
		try{
			this.userMapper.updateByPrimaryKey(record);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}
	
	@Override
	public boolean saveUserRoles(List<UserRole> roles) {
		try{
			if(CollectionUtils.isNotEmpty(roles)){
				for(UserRole role : roles){
					this.userRoleMapper.insert(role);
				}
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean deleteUserRoles(List<String> userCodes) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInString("user_code", userCodes);
		try{
			this.userRoleMapper.deleteByExample(example);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean existsName(String loginName) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("login_code", StringUtils.trim(loginName));
		try{
			Integer rows = this.userMapper.countByExample(example);
			return (rows > 0);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public PaginationResultSet<User> findUsersByCenterManagePagination(UserVO vo) {
		PaginationResultSet<User> result = new PaginationResultSet<User>();
		if(StringUtils.isNotEmpty(vo.getAreaCode())){
			vo.setAreaCode(StringUtils.trim(vo.getAreaCode())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getName())){
			vo.setName("%"+StringUtils.trim(vo.getName())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getOrganName())){
			vo.setOrganName("%"+StringUtils.trim(vo.getOrganName())+"%");
		}
		try{
			Long rows = this.userMapper.findUsersByCenterManagePaginationRows(vo);
			if(rows > 0){
				result.setRows(this.userMapper.findUsersByCenterManagePagination(vo));
			}
			result.setTotal(rows);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return result;
	}

	@Override
	public PaginationResultSet<User> findUsersByAdminManagePagination(UserVO vo) {
		PaginationResultSet<User> result = new PaginationResultSet<User>();
		if(StringUtils.isNotEmpty(vo.getAreaCode())){
			vo.setAreaCode(StringUtils.trim(vo.getAreaCode())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getName())){
			vo.setName("%"+StringUtils.trim(vo.getName())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getOrganName())){
			vo.setOrganName("%"+StringUtils.trim(vo.getOrganName())+"%");
		}
		try{
			Long rows = this.userMapper.findUsersByAdminManagePaginationRows(vo);
			if(rows > 0){
				result.setRows(this.userMapper.findUsersByAdminManagePagination(vo));
			}
			result.setTotal(rows);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return result;
	}

	
	public List<UserRole> findUserRoleByUserCode(String userCode){
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(userCode)){
			criteria.andEqualTo("user_code", userCode);
		}
		return this.userRoleMapper.selectByExample(example);
	}

	@Override
	public User getUserByUserCode(String userCode) {
		if (StringUtils.isEmpty(userCode)) {
			return null;
		}
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("unique_code", userCode);
		List<User> result = userMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(result)){
			return result.get(0);
		}
		return null;
	}

	@Override
	public boolean existsMavinUser(String mavinCode) {
		if (StringUtils.isEmpty(mavinCode)) {
			return false;
		}
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("user_type", UserCategory.MAVIN_USER.getCode())
		.andEqualTo("mavin_code", StringUtils.trimToEmpty(mavinCode));
		Integer result = userMapper.countByExample(example);
		
		return (result > 0);
	}

	@Override
	public boolean existsOrganUser(String organCode) {
		if (StringUtils.isEmpty(organCode)) {
			return false;
		}
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInString("user_type", Arrays.asList(UserCategory.ADMIN_MANAGE_USER.getCode(), UserCategory.BASIC_HOSPITAL_USER.getCode()))
		.andEqualTo("user_unit", StringUtils.trimToEmpty(organCode));
		Integer result = userMapper.countByExample(example);
		
		return (result > 0);
	}
}
