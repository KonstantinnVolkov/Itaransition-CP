package com.example.deploy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] FOR_ADMINS = {
            "/deletePost/**",
            "/postEditor/**",
            "/admin_panel/**",
            "/makeAdmin/**",
            "/blockUser/**",
            "/deleteUser/**"
    };

    private static final String[] staticResources  =  {
            "/css/**",
            "/images/**",
            "/fonts/**",
            "/scripts/**",
    };


    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/feed/**/").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/profile/**/").authenticated()
                .antMatchers(FOR_ADMINS).hasAuthority("ADMIN")
                .and()
                .formLogin()
                .usernameParameter("userName")
                .loginPage("/login")
                .defaultSuccessUrl("/feed")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
