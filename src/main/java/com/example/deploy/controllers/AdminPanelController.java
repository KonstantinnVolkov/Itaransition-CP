package com.example.deploy.controllers;

import com.example.deploy.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_panel")
public class AdminPanelController {

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @GetMapping
    public String getAdminPage(Model model){
        model.addAttribute("allUsers", userDetailService.getAllUsers());
        return "adminPanel";
    }
}
