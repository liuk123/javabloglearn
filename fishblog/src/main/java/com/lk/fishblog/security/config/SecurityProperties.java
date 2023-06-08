package com.lk.fishblog.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "demo.security")
@Configuration
@Data
public class SecurityProperties {
    private String loginPage = "http://www.cicode.cn/user/login";
    private String loginProcessingUrl = "http://www.cicode.cn/user/login";
    private String logoutUrl = "/user/logout";

    private String[] matchers = new String[]{
            "/user/**",
            "/menu/**",
            "/focus/**",
            "/assets/**"
    };
    private String[] regexMatchers = new String[]{

    };

}