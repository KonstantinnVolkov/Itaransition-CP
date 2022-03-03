package com.example.deploy.controllers;

import com.example.deploy.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedContoller {

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @GetMapping("/feed")
    public String getFeedPage(Authentication authentication, Model model){
        boolean isAuthenticated;
        if (authentication != null){
            isAuthenticated = authentication.isAuthenticated();
            model.addAttribute("authentication", isAuthenticated);
            if (isAuthenticated){
            }
        }
        else {
            model.addAttribute("authentication", false);
        }
        return "feed";
    }
}
