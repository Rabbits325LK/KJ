package com.keepjob.core.webtemplate;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;

public interface WebTemplateDAO {

	/**
	 * 获取
	 * @param id
	 * @return
	 */
	public WebTemplate getWebTemplate(Integer id);
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	public PaginationResultSet<WebTemplate> findWebTemplateByPagination(WebTemplateVO vo);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public boolean deleteWebTemplates(List<Integer> ids);
	
	/**
	 * 保存
	 * @param record
	 * @return
	 */
	public boolean saveWebTemplate(WebTemplate record);
	
	/**
	 * 更新
	 * @param record
	 * @return
	 */
	public boolean updateWebTemplate(WebTemplate record);
}
