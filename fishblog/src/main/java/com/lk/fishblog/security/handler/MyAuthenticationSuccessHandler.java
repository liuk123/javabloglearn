package com.lk.fishblog.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lk.fishblog.controller.request.NewUserResponse;
import com.lk.fishblog.model.Authority;
import com.lk.fishblog.model.Role;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 自定义登录成功后的处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User u = (User) authentication.getPrincipal();
        List<Authority> authors = new ArrayList<>();
        for(UserGroup ug:u.getUserGroupList()){
            List<Role> roleList = ug.getRoleList();
            for(Role role: roleList){
                authors.addAll(role.getAuthorityList());
            }

        }
        for(Role role: u.getRoleList()){
            authors.addAll(role.getAuthorityList());
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("resultCode",1);
        map.put("resultMessage","登录成功");
        map.put("data",new NewUserResponse(
                u.getId(),
                u.getUsername(),
                u.getAvatar(),
                u.getEmail(),
                u.getCreateTime(),
                authors
        ));
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();

    }

}