package com.lk.fishblog.security.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    //decide 方法是判定是否拥有权限的决策方法
    //authentication是UserDetailsServiceImpl中循环添加到GrantedAuthority 对象中的权限信息集合
    //object包含客户端发起的请求的request信息，可转换为 HttpServletRequest request=((FilterInvocation) object)
    //.getHttpRequest();
    //configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
    //此方法是为了判定用户请求的Url 是否在权限表中，如果在权限表中，则返回decide方法，用来判定用户是否由此权限，如果不在权限表中则放行。

    @Override
    public void decide(
            Authentication authentication,
            Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if(null== configAttributes || configAttributes.size() <=0) {
            return;
            //说明请求的系统中不存在指定的URL，返回执行security配置文件中其他项目。
        }
        String needRole;
        for (ConfigAttribute configAttribute : configAttributes) {
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限访问");
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