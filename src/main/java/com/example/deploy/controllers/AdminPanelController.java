package com.example.deploy.controllers;

import com.example.deploy.DTO.user.UserAdminDTO;
import com.example.deploy.mappers.UserMapper;
import com.example.deploy.models.Role;
import com.example.deploy.models.State;
import com.example.deploy.services.user.UserDetailsServiceImpl;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminPanelController {

    private final UserDetailsServiceImpl userDetailService;
    private final UserService userService;

    @Autowired
    public AdminPanelController(UserDetailsServiceImpl userDetailService, UserService userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @GetMapping("/admin_panel")
    public String getAdminPage(Model model){
        List<UserAdminDTO> list = UserMapper.mapEntityToUserAdminDTO(userDetailService.getAllUsersSortedById());
        model.addAttribute("allUsers", list);
        return "adminPanel";
    }

    @PostMapping("/giveRole")
    public String setRole(@RequestParam("id") long id,
                          @RequestParam("role") Role role){
        userService.updateRights(id, role);
        return "redirect:/adminPanel";
    }

    @PostMapping("/setStatus")
    public String setStatus(@RequestParam("id") long id,
                            @RequestParam("state") State state){
        userService.updateStatus(id, state);
        return "redirect:/adminPage";
    }

}
