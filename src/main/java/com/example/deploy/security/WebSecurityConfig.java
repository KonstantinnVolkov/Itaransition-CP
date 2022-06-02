package com.example.deploy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] FOR_ADMINS = {
            "/admin_panel/**",
            "/giveRole/**",
            "/setStatus/**",
            "saveRate/**"
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
    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                            UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                //authentication section
                .antMatchers(staticResources).permitAll()
                .antMatchers("/feed/**/").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/profile/**/").authenticated()
                .antMatchers(FOR_ADMINS).hasAuthority("ADMIN");

        http
                .formLogin()            //login page setup
                .usernameParameter("userName")
                .loginPage("/login")
                .defaultSuccessUrl("/feed")
                .and()
                .logout()
                .logoutSuccessUrl("/login");
        http
                .rememberMe()                       //remember-me setup
                .key("$2fuckBSEU2$")
                .userDetailsService(userDetailsService)
                .rememberMeParameter("remember-me")         //remember-me parameter name, by default spring uses "remember-me" but let it be
                .rememberMeCookieName("rememberLogin")
                .tokenValiditySeconds(86400);               //token will be valid for 24 hours
        http
                .csrf().disable();
    }
}
