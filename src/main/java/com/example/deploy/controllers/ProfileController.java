package com.example.deploy.controllers;

import com.example.deploy.mappers.PostMapper;
import com.example.deploy.mappers.UserMapper;
import com.example.deploy.models.Role;
import com.example.deploy.models.User;
import com.example.deploy.services.post.PostServiceImpl;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String getProfilePage(@RequestParam("username") String username,
                                 @RequestParam("id") long user_id,
                                 Model model,
                                 Authentication authentication,
                                 Principal principal) {
        if (!authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User currentUser = userService.getUserByUsername(principal.getName());  //Забираем текущего залогиненого пользователя
        if ( (userService.getUserByUsername(username) == null || userService.getUserById(user_id) == null)  //Что бы при изменении параметров URL'а
                    || !(userService.getUserByUsername(username).getId().equals(user_id))) {                    //не переходило хер пойми куда
            return String.format("redirect:/profile?id=%d&username=%s", currentUser.getId(), currentUser.getUserName());
        }
        if (currentUser.getRole().equals(Role.ADMIN) ||        //Может ли пользователь редактировать и создавать свои и чужие посты
                (currentUser.getUserName().equals(username) && currentUser.getId().equals(user_id))) {
            model.addAttribute("isEditEnabled", true);
        }
        else {
            model.addAttribute("isEditEnabled", false);
        }
        if (currentUser.getRole().equals(Role.ADMIN)) {
            model.addAttribute("isAdmin", true);
        }
        else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("user", UserMapper.mapEntityToProfileDTO(userService.getUserByUsername(username)));
        model.addAttribute("posts", PostMapper.mapEntityToProfileDTO(postService.getAllPostsByAuthorId(user_id)));
        return "profile";
    }
}
