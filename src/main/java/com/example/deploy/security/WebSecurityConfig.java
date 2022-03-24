package com.example.deploy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/scripts/**",
        };
        http
                .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/feed/**/").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/admin_panel/**").hasAuthority("ADMIN")
                .antMatchers("/profile/**/").authenticated()
                .antMatchers("/postEditor/**").hasAuthority("ADMIN")
                .antMatchers("/deletePost/**").hasAuthority("ADMIN")
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
