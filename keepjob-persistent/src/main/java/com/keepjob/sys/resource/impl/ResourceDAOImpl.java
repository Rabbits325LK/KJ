package com.keepjob.sys.resource.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keepjob.common.enums.Status;
import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.util.CollectionUtils;
import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceDAO;
import com.keepjob.sys.resource.ResourceMapper;
import com.keepjob.sys.user.UserRoleMapper;

@Component("resourceDAO")
public class ResourceDAOImpl implements ResourceDAO {
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public List<Resource> findResourceByUserCode(String userCode) {
		try{
			return this.resourceMapper.findResourceByUserCode(userCode);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<Resource> findResourceByRoleCode(String roleCode) {
		try{
			return this.resourceMapper.findResourceByRoleCode(roleCode);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public Integer getResourceRows(String code, String parentCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		
		if (StringUtils.isNotEmpty(code)) {
			criteria.andEqualTo("code", StringUtils.trimToEmpty(code));
		}
		if (StringUtils.isNotEmpty(parentCode)) {
			criteria.andEqualTo("parent_code", StringUtils.trimToEmpty(parentCode));
		}
		try{
			return resourceMapper.countByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}
	
	@Override
	public boolean saveResource(Resource record) {
		try{
			this.resourceMapper.insert(record);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public Resource getResource(String code) {
		try{
			return this.resourceMapper.selectByPrimaryKey(code);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean updateResource(Resource record) {
		try{
			this.resourceMapper.updateByPrimaryKey(record);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public boolean deleteResource(String code) {
		try{
			this.resourceMapper.deleteByPrimaryKey(code);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<Resource> findResources(String url) {
		MyBatisCriteria example = new MyBatisCriteria();
		example.createCriteria().andEqualTo("status", Status.ENABLE.getCode()).andEqualTo("url", url);
		try{
			return this.resourceMapper.selectByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public List<Resource> findResourceByParentCode(String parentCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		if (StringUtils.isNotEmpty(parentCode)) {
			example.createCriteria().andEqualTo("parent_code", parentCode);
		}
		try{
			return resourceMapper.selectByExample(example);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}

	@Override
	public String getMaxCodeByParentCode(String parentCode) {
		MyBatisCriteria example = new MyBatisCriteria();
		if (StringUtils.isNotEmpty(parentCode)) {
			example.createCriteria().andEqualTo("parent_code", parentCode);
		}
		example.setOrderByClause("code desc");
		try{
			List<Resource> result = resourceMapper.selectByExample(example);
			if(CollectionUtils.isNotEmpty(result)){
				return result.get(0).getCode();
			}else{
				return StringUtils.trimToEmpty(parentCode)+"01";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new DatabaseException(ex);
		}
	}
	
}
