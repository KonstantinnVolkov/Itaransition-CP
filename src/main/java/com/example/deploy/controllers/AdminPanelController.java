package com.example.deploy.controllers;

import com.example.deploy.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @GetMapping("/admin_panel")
    public String getAdminPage(Model model){
        model.addAttribute("allUsers", userDetailService.getAllUsers());
        return "adminPanel";
    }
}
