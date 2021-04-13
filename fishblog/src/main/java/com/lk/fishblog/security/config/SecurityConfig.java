package com.lk.fishblog.security.config;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.lk.fishblog.security.MyCustomUserService;
import com.lk.fishblog.security.MyPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersistentTokenRepository persistentTokenRepository;

    private final MyPasswordEncoder myPasswordEncoder;

    private final MyCustomUserService myCustomUserService;

    private final ObjectMapper objectMapper;

    private final SecurityProperties securityProperties;

    public SecurityConfig(MyPasswordEncoder myPasswordEncoder, MyCustomUserService myCustomUserService, ObjectMapper objectMapper, SecurityProperties securityProperties, PersistentTokenRepository persistentTokenRepository) {
        this.myPasswordEncoder = myPasswordEncoder;
        this.myCustomUserService = myCustomUserService;
        this.objectMapper = objectMapper;
        this.securityProperties = securityProperties;
        this.persistentTokenRepository = persistentTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authenticationProvider(authenticationProvider())

            .httpBasic()
            //未登录时，进行json格式的提示
//            .authenticationEntryPoint((request,response,authException) -> {
//                response.setContentType("application/json;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                PrintWriter out = response.getWriter();
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("resultCode",0);
//                map.put("resultMessage","未登录");
//                out.write(objectMapper.writeValueAsString(map));
//                out.flush();
//                out.close();
//            })

            .and()
            .authorizeRequests()//权限
            .antMatchers(securityProperties.getMatchers()).permitAll()//不拦截这些请求
            .regexMatchers(securityProperties.getRegexMatchers()).permitAll()
//            .regexMatchers(HttpMethod.GET, "/article").hasAnyAuthority("normal")
            .antMatchers("/user/**").permitAll()
            .antMatchers("/menu/**").permitAll()
            .anyRequest()
            .authenticated()

            .and()
            .formLogin() //使用自带的登录
            .usernameParameter("username")
            .passwordParameter("password")
            .loginPage(securityProperties.getLoginPage())
            .loginProcessingUrl(securityProperties.getLoginProcessingUrl())
            //登录失败，返回json
            .failureHandler((request,response,ex) -> {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resultCode",0);
                if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                    map.put("resultMessage","用户名或密码错误");
                } else if (ex instanceof DisabledException) {
                    map.put("resultMessage","账户被禁用");
                } else {
                    map.put("resultMessage","登录失败!");
                }
                out.write(objectMapper.writeValueAsString(map));
                out.flush();
                out.close();
            })
            //登录成功，返回json
//            .successHandler(new MyAuthenticationSuccessHandler("www.baidu.com"))
            .successHandler((request,response,authentication) -> {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resultCode",1);
                map.put("resultMessage","登录成功");
                map.put("data",authentication.getPrincipal());
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(objectMapper.writeValueAsString(map));
                out.flush();
                out.close();
            })

            //记住我功能
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
            .exceptionHandling()
            //没有权限，返回json
            .accessDeniedHandler((request,response,ex) -> {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter out = response.getWriter();
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resultCode",0);
                map.put("resultMessage", "权限不足");
                out.write(objectMapper.writeValueAsString(map));
                out.flush();
                out.close();
            })

            .and()
            .logout()
            .logoutUrl(securityProperties.getLogoutUrl())
            .logoutSuccessUrl(securityProperties.getLoginPage())
            .invalidateHttpSession(true)//是session失效
            .deleteCookies("JSESSIONID")
            //退出成功，返回json
            .logoutSuccessHandler((request,response,authentication) -> {
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("resultCode",1);
                map.put("resultMessage","退出成功");
                map.put("data",authentication);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(objectMapper.writeValueAsString(map));
                out.flush();
                out.close();
            });

        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();


    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myCustomUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myCustomUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

}