package com.example.deploy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @GetMapping("/profile{username}")
    public String getProgilePage(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        model.addAttribute("username", username);
        return "profile";
    }
}
