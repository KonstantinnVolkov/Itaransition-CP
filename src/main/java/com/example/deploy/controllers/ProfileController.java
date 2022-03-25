package com.example.deploy.controllers;

import com.example.deploy.mappers.PostMapper;
import com.example.deploy.mappers.UserMapper;
import com.example.deploy.models.Role;
import com.example.deploy.models.User;
import com.example.deploy.services.PostServiceImpl;
import com.example.deploy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ProfileController {

    private final PostServiceImpl postService;
    private final UserService userService;

    @Autowired
    public ProfileController(PostServiceImpl postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProgilePage(@RequestParam("username") String username,
                                 @RequestParam("id") long user_id,
                                 Model model,
                                 Principal principal) {
        User currentUser = userService.getUserByUsername(principal.getName());  //Забираем текущего залогиненого пользователя
        if (currentUser.getRole().equals(Role.ADMIN) ||        //Может ли пользователь редактировать и создавать свои и чужие посты
                (currentUser.getUserName().equals(username) && currentUser.getId().equals(user_id))) {
            model.addAttribute("isEditEnabled", true);
        }
        else {
            model.addAttribute("isEditEnabled", false);
        }
//        model.addAttribute("user", userService.getUserByUsername(username));
        model.addAttribute("user", UserMapper.mapEntityToProfileDTO(userService.getUserByUsername(username)));
        model.addAttribute("posts", PostMapper.mapEntityToDTO(postService.getAllPostsByAuthorId(user_id)));
        return "profile";
    }
}
