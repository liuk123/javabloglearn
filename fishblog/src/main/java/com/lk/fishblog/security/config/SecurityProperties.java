package com.lk.fishblog.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "demo.security")
@Configuration
@Data
public class SecurityProperties {
    private String loginPage = "/user/login";
    private String loginProcessingUrl = "/user/login";
    private String logoutUrl = "/user/logout";

    private String[] matchers = new String[]{
            "/js/**",
            "/user/**",
            "/menu/**",
    };
    private String[] regexMatchers = new String[]{

    };

}