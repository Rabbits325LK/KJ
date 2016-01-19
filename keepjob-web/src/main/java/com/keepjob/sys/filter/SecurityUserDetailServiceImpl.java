package com.keepjob.sys.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.keepjob.common.exception.ApplicationException;
import com.keepjob.sys.resource.Resource;
import com.keepjob.sys.resource.ResourceHandler;
import com.keepjob.sys.user.User;
import com.keepjob.sys.user.UserHandler;
import com.keepjob.sys.user.UserStatus;

public class SecurityUserDetailServiceImpl  implements UserDetailsService {
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private ResourceHandler resourceHandler;
	
	@Override
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
		Collection<GrantedAuthority> grantedAuths = null;
		
		User user=userHandler.getUserByLoginName(StringUtils.trim(loginName));
        if(null == user){
            throw new UsernameNotFoundException("登录名["+StringUtils.trim(loginName)+"]对应用户信息不存在。");
        }
        
        try{
        	grantedAuths=obtionGrantedAuthorities(user);
        }catch(Exception ex){
        	ex.printStackTrace();
        	throw new ApplicationException("设置用户角色错误。");
        }

        org.springframework.security.core.userdetails.User result = new org.springframework.security.core.userdetails.User(user.getLoginCode(), user.getPassword(), 
        		(UserStatus.ENABLE.getCode().equals(StringUtils.trim(user.getStatus()))), true, true, true, grantedAuths);
        
        return result;
	}
	
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {  
		Set<GrantedAuthority> result = new HashSet<GrantedAuthority>();
		List<Resource> resources = this.resourceHandler.findResourcesByUserCode(user.getUniqueCode());
		for(Resource resource : resources){
			result.add(new SimpleGrantedAuthority(resource.getCode()));
		}
		return result;
	}
}
