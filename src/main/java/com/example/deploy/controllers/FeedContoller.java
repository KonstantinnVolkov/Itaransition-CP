package com.example.deploy.controllers;

import com.example.deploy.mappers.PostMapper;
import com.example.deploy.services.post.PostServiceImpl;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class FeedContoller {

    private final PostServiceImpl postService;
    private final UserService userService;

    @Autowired
    public FeedContoller(PostServiceImpl postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/feed")
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
        model.addAttribute("allPosts", PostMapper.mapEntityToFeedDTO(postService.getPostByDateDesc()));
        return "feed";
    }
}