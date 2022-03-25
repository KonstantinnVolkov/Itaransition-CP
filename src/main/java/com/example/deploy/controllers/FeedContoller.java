package com.example.deploy.controllers;

import com.example.deploy.models.Post;
import com.example.deploy.services.PostServiceImpl;
import com.example.deploy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class FeedContoller {

    private final PostServiceImpl postService;
    private final UserService userService;

    @Autowired
    public FeedContoller(PostServiceImpl postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping("/feed")
    public String getFeedPage(Model model, Principal principal,
                              Authentication authentication) {
        boolean isAuthenticated;
        if (authentication != null) {
            isAuthenticated = authentication.isAuthenticated();
            model.addAttribute("authentication", isAuthenticated);
            if (isAuthenticated) {
                model.addAttribute("username", principal.getName());
                model.addAttribute("userId", userService.getUserByUsername(principal.getName()).getId());
            }
        }
        List<Post> allPosts = postService.getPostByDateDesc();
        model.addAttribute("allPosts", allPosts);
        return "feed";
    }
}