package com.keepjob.sys.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.util.NumberUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.role.Role;
import com.keepjob.sys.role.RoleDAO;
import com.keepjob.sys.role.RoleMapper;
import com.keepjob.sys.role.RoleResource;
import com.keepjob.sys.role.RoleResourceMapper;

@Repository("roleDAO")
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	@Override
	public boolean saveRole(Role record) {
		try{
			this.roleMapper.insert(record);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}

	@Override
	public boolean updateRole(Role record) {
		try{
			this.roleMapper.updateByPrimaryKey(record);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}


	@Override
	public Role getRole(Integer id) {
		try{
			return this.roleMapper.selectByPrimaryKey(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean saveResources(List<RoleResource> records) {
		try{
			for(RoleResource record : records){
				this.roleResourceMapper.insert(record);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}

	@Override
	public boolean deleteResources(List<String> roleCodes) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInString("role_code", roleCodes);
		try{
			this.roleResourceMapper.deleteByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
		return true;
	}

	@Override
	public List<Role> findEnableRoles() {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("status", Status.ENABLE.getCode());
		try{
			return this.roleMapper.selectByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean existsName(Integer id, String name) {
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(name)){
			criteria.andEqualTo("name", StringUtils.trim(name));
		}
		if(!NumberUtils.isEmpty(id)){
			criteria.andNotEqualTo("id", id);
		}
		try{
			Integer rows = this.roleMapper.countByExample(example);
			return (rows > 0);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<Role> findRolesByUserCode(String userCode) {
		try{
			return this.roleMapper.findRolesByUserCode(StringUtils.trim(userCode));
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<RoleResource> findRoleResources(String url) {
		try{
			return this.roleResourceMapper.findRoleResources(StringUtils.trim(url));
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean deleteRoleResourceByResourceCode(String resourceCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("resource_code", resourceCode);
		try{
			roleResourceMapper.deleteByExample(example);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<RoleResource> findRoleByResources(String roleCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(roleCode)){
			criteria.andEqualTo("role_code", roleCode);
		}
		try{
			return this.roleResourceMapper.selectByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean deleteRoles(List<Integer> ids) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andInInteger("id", ids);
		try{
			this.roleMapper.deleteByExample(example);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}
}
