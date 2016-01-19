package com.keepjob.sys.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.keepjob.common.util.StringUtils;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceHandler;


public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@javax.annotation.Resource
	private ResourceHandler resourceHandler;
	
	/**
	 *  返回所请求资源所需要的权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		ConfigAttribute attribute = null;
		
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		/**
		 *
		if(!StringUtils.trimToEmpty(requestUrl).endsWith(".js") && !StringUtils.trimToEmpty(requestUrl).endsWith(".css") 
			&& !StringUtils.trimToEmpty(requestUrl).endsWith(".dll") && !StringUtils.trimToEmpty(requestUrl).endsWith(".jpg")
			 && !StringUtils.trimToEmpty(requestUrl).endsWith(".png")){
		 
		 */
			List<Resource> resources = this.resourceHandler.findResources(StringUtils.trimToEmpty(requestUrl));
			
			if (CollectionUtils.isEmpty(resources)) {
				return attributes;
			}
			for (Resource item : resources) {
				attribute = new SecurityConfig(item.getCode());
				attributes.add(attribute);
			}
		/**}else{
			attribute = new SecurityConfig("1");
			attributes.add(attribute);
		}
		*/
		return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
}
