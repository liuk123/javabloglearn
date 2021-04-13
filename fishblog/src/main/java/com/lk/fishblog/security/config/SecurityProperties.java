package com.lk.fishblog.security.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@ConfigurationProperties(prefix = "demo.security")
@Configuration
@Data
public class SecurityProperties {
    private String loginPage = "/user/login.html";
    private String loginProcessingUrl = "/user/login";
    private String logoutUrl = "/user/logout";

    private String[] matchers = new String[]{
            "/js/**",
    };
    private String[] regexMatchers = new String[]{

    };
    // 默认登录页
//    private String loginPageHtml = "signin";

}