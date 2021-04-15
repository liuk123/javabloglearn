package com.lk.fishblog.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/*
 * 自定义登录成功后的处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    // 构造方法
    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//        // 获取用户
//        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
//        // 打印用户名
//        System.out.println(user.getUsername());
//        // 密码，出于安全考虑，Spring Security这里会返回一个Null
//        System.out.println(user.getPassword());
//        // 权限
//        System.out.println(user.getAuthorities());
//        // 这里使用跳转
//        response.sendRedirect(url);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("resultCode",1);
        map.put("resultMessage","登录成功");
        map.put("data",authentication.getPrincipal());
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();

    }

}