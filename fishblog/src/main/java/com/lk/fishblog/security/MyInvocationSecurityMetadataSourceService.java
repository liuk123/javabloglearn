package com.lk.fishblog.security;

import com.lk.fishblog.model.Authority;
import com.lk.fishblog.repository.AuthorityRepository;
import com.lk.fishblog.repository.UserRepository;
import com.lk.fishblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    private HashMap<String, Collection<ConfigAttribute>> map = null;
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        List<Authority> permissions=authorityRepository.findAll();
        for(Authority permission:permissions){
            array = new ArrayList<>();
            array.add(new SecurityConfig(permission.getName()));
            map.put(permission.getUrl(),array);
        }
    }
/*
     * 此方法是为了判定用户请求的url是否再权限表中，如果在权限表中，则返回给decide方法，
     * 用来判定用户是否有此权限。如果不在权限表中则放行。
     *
     * 方法的目的是：确定该请求是否需要进行访问权限的判断，对于需要判断权限的请求，返回resUrl，对于不需要
     * 进行权限判断的请求，返回Null

*/
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            //当url里有？的时候  进行切割
            if(resUrl.indexOf("?")>-1){
                resUrl=resUrl.substring(0,resUrl.indexOf("?"));
            }
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
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
