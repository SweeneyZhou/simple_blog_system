package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService = null;

    /**
     * 配置请求拦截
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        http
                .authorizeRequests()
                //权限限制
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                //开放其他请求
                .anyRequest().permitAll()

                .and().anonymous()//开放请求允许匿名访问

                .and().formLogin().loginPage("/login").defaultSuccessUrl("/admin")
                //开启表单登录，配置登录页面
                //开启HTTP基础验证
                .and().logout().logoutSuccessUrl("/")
                .and().httpBasic();

    }

    /**
     * 配置签名管理 构建具体的用户权限控制
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth
                .userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
