package com.keepjob.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {
	// 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，
    // 其他的两个组件，已经在AbstractSecurityInterceptor定义
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
    	String uri=((HttpServletRequest)request).getRequestURI();
    	String path =((HttpServletRequest)request).getContextPath();
    	
    	if(uri.indexOf("/login.html")<=0 && !uri.equals(new StringBuffer(path).append("/").toString())){
    		 FilterInvocation fi = new FilterInvocation(request, response, chain);   
    		 invoke(fi);
    	}else{
    		chain.doFilter(request, response);
    	}
    }
    
    private void invoke(FilterInvocation fi) throws IOException,ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

}
