package com.lk.fishblog.security.config;

import com.lk.fishblog.security.MyCustomUserService;
import com.lk.fishblog.security.MyPasswordEncoder;
import com.lk.fishblog.security.filter.MyFilterSecurityInterceptor;
import com.lk.fishblog.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.lk.fishblog.security.handler.MyLogoutSuccessHandler;
import com.lk.fishblog.security.handler.MyAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersistentTokenRepository persistentTokenRepository;

    private final MyPasswordEncoder myPasswordEncoder;

    private final MyCustomUserService myCustomUserService;
    private final MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    private final SecurityProperties securityProperties;
    private final MyAuthenticationFailureHandler authenticationFailureHandler;

    final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestfulAccessDeniedHandler accessDeniedHandler;
    final MyLogoutSuccessHandler myLogoutSuccessHandler;
    final MyAuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(MyPasswordEncoder myPasswordEncoder, MyCustomUserService myCustomUserService,
                          SecurityProperties securityProperties, PersistentTokenRepository persistentTokenRepository,
                          RestAuthenticationEntryPoint restAuthenticationEntryPoint, MyAuthenticationFailureHandler authenticationFailureHandler,
                          RestfulAccessDeniedHandler accessDeniedHandler,
                          MyLogoutSuccessHandler myLogoutSuccessHandler, MyAuthenticationSuccessHandler authenticationSuccessHandler, MyFilterSecurityInterceptor myFilterSecurityInterceptor) {
        this.myPasswordEncoder = myPasswordEncoder;
        this.myCustomUserService = myCustomUserService;
        this.securityProperties = securityProperties;
        this.persistentTokenRepository = persistentTokenRepository;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.myFilterSecurityInterceptor = myFilterSecurityInterceptor;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()//权限
            .antMatchers("/user/**").permitAll()
            .antMatchers(securityProperties.getMatchers()).permitAll()//不拦截这些请求
            .regexMatchers(securityProperties.getRegexMatchers()).permitAll()
//            .regexMatchers(HttpMethod.GET, "/article").hasAnyAuthority("normal")
            .anyRequest()
            .authenticated()

            .and()
            .formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage(securityProperties.getLoginPage())
            .loginProcessingUrl(securityProperties.getLoginProcessingUrl())
            .failureHandler(authenticationFailureHandler)
            .successHandler(authenticationSuccessHandler)

            .and()
            .rememberMe()
            //参数名，和表单中的一样
            .rememberMeParameter("remember")
            //持久层对象
            .tokenRepository(persistentTokenRepository)
            //登录逻辑设置
            .userDetailsService(myCustomUserService)
            //失效时间，默认为两周，这里设为60秒
            .tokenValiditySeconds(60)

            .and()
            .logout()
            .logoutUrl(securityProperties.getLogoutUrl())
            .logoutSuccessUrl(securityProperties.getLoginPage())
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(myLogoutSuccessHandler);


        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
        // 无权访问 JSON 格式的数据
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myCustomUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

}