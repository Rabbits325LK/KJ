package com.keepjob.core.webtemplate.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepjob.common.exception.DatabaseException;
import com.keepjob.common.mybatis.MyBatisCriteria;
import com.keepjob.common.mybatis.MyBatisCriteria.Criteria;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.webtemplate.WebTemplate;
import com.keepjob.core.webtemplate.WebTemplateDAO;
import com.keepjob.core.webtemplate.WebTemplateMapper;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;

@Repository("webTemplateDAO")
public class WebTemplateDAOImpl implements WebTemplateDAO{

	@Autowired
	private WebTemplateMapper webTemplateMapper;
	
	@Override
	public WebTemplate getWebTemplate(Integer id) {
		// TODO Auto-generated method stub
		try {
			return this.webTemplateMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public PaginationResultSet<WebTemplate> findWebTemplateByPagination(
			WebTemplateVO vo) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		Criteria criteria = example.createCriteria();
		PaginationResultSet<WebTemplate> result = new PaginationResultSet<WebTemplate>();
		if(StringUtils.isNotEmpty(vo.getUniqueCode())){
			criteria.andEqualTo("unique_code", StringUtils.trimToEmpty(vo.getUniqueCode()));
		}
		if(StringUtils.isNotEmpty(vo.getExplainTitle())){
			criteria.andLike("explanin_title", "%"+StringUtils.trimToEmpty(vo.getExplainTitle())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getIntroductionTitle())){
			criteria.andLike("introduction_title", "%"+StringUtils.trimToEmpty(vo.getIntroductionTitle())+"%");
		}
		if(StringUtils.isNotEmpty(vo.getModelType())){
			criteria.andEqualTo("model_type", StringUtils.trimToEmpty(vo.getModelType()));
		}
		if(StringUtils.isNotEmpty(vo.getStatus())){
			criteria.andEqualTo("status", StringUtils.trimToEmpty(vo.getStatus()));
		}
		example.setOrderByClause("create_date");
		try {
			Integer rows = this.webTemplateMapper.countByExample(example);
			if(rows > 0){
				result.setRows(this.webTemplateMapper.selectByExample(example));
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
	public boolean deleteWebTemplates(List<Integer> ids) {
		// TODO Auto-generated method stub
		MyBatisCriteria example = new MyBatisCriteria();
		if(CollectionUtils.isNotEmpty(ids)){
			example.createCriteria().andInInteger("id", ids);
		}
		try {
			this.webTemplateMapper.deleteByExample(example);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean saveWebTemplate(WebTemplate record) {
		// TODO Auto-generated method stub
		try {
			this.webTemplateMapper.insert(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public boolean updateWebTemplate(WebTemplate record) {
		// TODO Auto-generated method stub
		try {
			this.webTemplateMapper.updateByPrimaryKey(record);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

}
