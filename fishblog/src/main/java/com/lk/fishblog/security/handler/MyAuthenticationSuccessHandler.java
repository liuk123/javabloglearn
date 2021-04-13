package com.lk.fishblog.security.handler;

import com.lk.fishblog.security.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 自定义登录成功后的处理器
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    // 构造方法
    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 获取用户
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        // 打印用户名
        System.out.println(user.getUsername());
        // 密码，出于安全考虑，Spring Security这里会返回一个Null
        System.out.println(user.getPassword());
        // 权限
        System.out.println(user.getAuthorities());
        // 这里使用跳转
        response.sendRedirect(url);

    }

}