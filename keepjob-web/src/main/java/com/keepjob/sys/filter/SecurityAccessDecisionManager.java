package com.keepjob.sys.filter;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.keepjob.common.Constant;
import com.keepjob.sys.user.User;


public class SecurityAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) throws AccessDeniedException, InsufficientAuthenticationException {
		ConfigAttribute attribute = null;
		if(org.apache.commons.collections.CollectionUtils.isEmpty(attributes)) {
			return;
		}
		//所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterator = attributes.iterator();
		User user = (User)((FilterInvocation) object).getHttpRequest().getSession().getAttribute(Constant.USER_KEY);
		if(null == user){
			throw new AccessDeniedException("当前用户已失效,请重新登录! ");
		}
		if (user.isSuper()) {
			return;
		}
		while(iterator.hasNext()) {
			attribute = iterator.next();
			//用户所拥有的权限authentication
			for(GrantedAuthority anth : authentication.getAuthorities()) {
				if(org.apache.commons.lang.StringUtils.trim(attribute.getAttribute()).equals(org.apache.commons.lang.StringUtils.trim(anth.getAuthority()))) {
					return;
				}
			}
		}
		//没有权限
		throw new AccessDeniedException("当前用户无没有访问权限。 ");
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
