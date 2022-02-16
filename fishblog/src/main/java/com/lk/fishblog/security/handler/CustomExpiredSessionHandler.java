package com.lk.fishblog.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author codermy
 * @createTime 2020/8/2
 */
@Component
public class CustomExpiredSessionHandler implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("resultCode",0);
        map.put("resultMsg", "已经另一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest());
        ObjectMapper objectMapper = new ObjectMapper();
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(objectMapper.writeValueAsString(map));
    }
}
