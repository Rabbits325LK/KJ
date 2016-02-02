package com.keepjob.core.webtemplate;

import java.util.List;

import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.core.members.Members;
import com.keepjob.core.webtemplate.vo.WebTemplateVO;
import com.keepjob.sys.user.User;

public interface WebTemplateHandler {

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
	public PaginationResultSet<WebTemplate> findByWebTemplateByPagination(WebTemplateVO vo);
	
	/**
	 * 维护
	 * @param members
	 * @param record
	 * @return
	 */
	public boolean saveOrUpdateWebTemplate(Members members, WebTemplate record);
	
	/**
	 * 批量删除
	 * @param members
	 * @param ids
	 * @return
	 */
	public boolean removeWebTemplates(Members members, List<Integer> ids);
	
	/**
	 * 批量暂存
	 * @param members
	 * @param ids
	 * @return
	 */
	public boolean temeporaryWebTemplates(Members members, List<Integer> ids);
	
	/**
	 * 批量发布
	 * @param members
	 * @param ids
	 * @return
	 */
	public boolean releaseWebTemplates(Members members, List<Integer> ids);
	
	/**
	 * 批量私有
	 * @param members
	 * @param ids
	 * @return
	 */
	public boolean privateWebTemplates(Members members, List<Integer> ids);
	
	/**
	 * 维护
	 * @param user
	 * @param record
	 * @return
	 */
	public boolean saveOrUpdateWebTemplate(User user, WebTemplate record);
	
	/**
	 * 批量删除
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean removeWebTemplates(User user, List<Integer> ids);
	
	/**
	 * 批量暂存
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean temeporaryWebTemplates(User user, List<Integer> ids);
	
	/**
	 * 批量发布
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean releaseWebTemplates(User user, List<Integer> ids);
	
	/**
	 * 批量私有
	 * @param user
	 * @param ids
	 * @return
	 */
	public boolean privateWebTemplates(User user, List<Integer> ids);
}
