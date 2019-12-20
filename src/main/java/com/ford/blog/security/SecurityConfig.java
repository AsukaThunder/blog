package com.ford.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ClassName: SecurityConfig
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 5:28
 *  Spring2.0以后不再提供关闭Security开关，默认是开启的
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * 配置一个userDetailsService Bean不在生成默认security.user用户
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
