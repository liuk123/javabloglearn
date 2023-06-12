package com.lk.fishblog.security.config;

import com.lk.fishblog.security.MyCustomUserService;
import com.lk.fishblog.security.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.lk.fishblog.security.handler.MyLogoutSuccessHandler;
import com.lk.fishblog.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyCustomUserService myCustomUserService;

    private final SecurityProperties securityProperties;
    private final MyAuthenticationFailureHandler authenticationFailureHandler;

    final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestfulAccessDeniedHandler accessDeniedHandler;
    final MyLogoutSuccessHandler myLogoutSuccessHandler;
    final MyAuthenticationSuccessHandler authenticationSuccessHandler;
    final CustomExpiredSessionHandler customExpiredSessionHandler;

    private final DataSource dataSource;

    public SecurityConfig(MyCustomUserService myCustomUserService,
                          SecurityProperties securityProperties,
                          RestAuthenticationEntryPoint restAuthenticationEntryPoint, MyAuthenticationFailureHandler authenticationFailureHandler,
                          RestfulAccessDeniedHandler accessDeniedHandler,
                          MyLogoutSuccessHandler myLogoutSuccessHandler, MyAuthenticationSuccessHandler authenticationSuccessHandler,
                          CustomExpiredSessionHandler customExpiredSessionHandler, DataSource dataSource) {
        this.myCustomUserService = myCustomUserService;
        this.securityProperties = securityProperties;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.myLogoutSuccessHandler = myLogoutSuccessHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.customExpiredSessionHandler = customExpiredSessionHandler;
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()//权限
//            .antMatchers("/user/**","/amount/**","/news/**").permitAll()//不拦截这些请求
            .antMatchers(securityProperties.getMatchers()).permitAll()
            .antMatchers(HttpMethod.GET, securityProperties.getMethodGETMatchers()).permitAll()
//            .antMatchers(HttpMethod.GET, "/article/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/comment/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/reply/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/tag/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/category/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/bookmark/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/menu/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/focus/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/friend/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/speak/**").permitAll()
//            .antMatchers(HttpMethod.GET, "/rss/**").permitAll()
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
            .tokenRepository(persistentTokenRepository())
            //登录逻辑设置
            .userDetailsService(myCustomUserService)
            //失效时间，默认为两周，这里设为60秒
            .tokenValiditySeconds(60*60*24*100)

            .and()
            .logout()
//            .logoutUrl(securityProperties.getLogoutUrl())
            .logoutRequestMatcher(new AntPathRequestMatcher(securityProperties.getLogoutUrl()))
            .logoutSuccessUrl(securityProperties.getLoginPage())
            .logoutSuccessHandler(myLogoutSuccessHandler)

            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

//            .and()
//            .sessionManagement()
//            .invalidSessionUrl("/user/invalid")
//            .maximumSessions(1)
//            // 当达到最大值时，是否保留已经登录的用户
//            .maxSessionsPreventsLogin(false)
//            // 当达到最大值时，旧用户被踢出后的操作
//            .expiredSessionStrategy(customExpiredSessionHandler);

        //开启跨域访问
//        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
//        http.csrf().disable();
//        ,"/amount/"
        http.csrf().ignoringAntMatchers("/news/").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/assets/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myCustomUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        // 自动建表，第一次运行设为true，以后都设为false
        jdbcTokenRepositoryImpl.setCreateTableOnStartup(false);
        return jdbcTokenRepositoryImpl;
    }
}