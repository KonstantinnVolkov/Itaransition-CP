package com.example.deploy.controllers;

import com.example.deploy.DTO.user.UserAdminDTO;
import com.example.deploy.mappers.UserMapper;
import com.example.deploy.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminPanelController {

    @Autowired
    private UserDetailsServiceImpl userDetailService;

    @GetMapping("/admin_panel")
    public String getAdminPage(Model model){
        List<UserAdminDTO> list = UserMapper.mapEntityToUserAdminDTO(userDetailService.getAllUsers());
        model.addAttribute("allUsers", list);
        return "adminPanel";
    }
}
