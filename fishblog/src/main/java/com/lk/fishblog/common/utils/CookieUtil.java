package com.lk.fishblog.common.utils;

import com.lk.fishblog.model.User;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieUtil {

    public static final String COOKIE_NAME_TOKEN = "token";
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    @Autowired
    private RedisUtil redisUtil;

    public void addCookie(HttpServletResponse response, String token, User user) {
        //将token存入到redis
        redisUtil.set(COOKIE_NAME_TOKEN + "::" + token, JSON.toJSONString(user), TOKEN_EXPIRE);
        //将token写入cookie
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public void removeCookie(HttpServletRequest request) {
        String paramToken = request.getParameter(COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, COOKIE_NAME_TOKEN);
        if (!StringUtils.isEmpty(cookieToken) || !StringUtils.isEmpty(paramToken)) {
            String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
            redisUtil.del(COOKIE_NAME_TOKEN + "::" + token);
        }
    }
    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
             return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = JSON.parseObject(redisUtil.get(COOKIE_NAME_TOKEN + "::" + token), User.class);
        //重置有效期
        if (user == null) {
            return null;
        }
        if (response != null) {
            addCookie(response, token, user);
        }
        return user;
    }
    public User getByToken(String token) {
        return getByToken(null, token);
    }
    public User getLoginUser(HttpServletRequest request) {
        return getLoginUser(request, null);
    }
    public User getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
             return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (response == null) {
            return getByToken(token);
        }
        return getByToken(response, token);
    }

}
