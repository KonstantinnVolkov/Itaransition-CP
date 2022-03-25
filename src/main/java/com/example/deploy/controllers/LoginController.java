package com.example.deploy.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication){
        if (authentication != null)
            return "redirect:/feed";
        return "login";
    }
}
