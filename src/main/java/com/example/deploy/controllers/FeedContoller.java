package com.example.deploy.controllers;

import com.example.deploy.models.User;
import com.example.deploy.services.UserDetailsServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FeedContoller {

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @RequestMapping("/feed")
    public String getFeedPage(@AuthenticationPrincipal User user, Authentication authentication, Model model, HttpServletRequest request){
        if (request) {

        }
        return "feed";
    }
}
