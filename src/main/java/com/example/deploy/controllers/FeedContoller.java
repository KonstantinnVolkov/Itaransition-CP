package com.example.deploy.controllers;

import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class FeedContoller {

    private final UserRepository userRepository;
    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public FeedContoller(PostService postService, PostRepository postRepository,
                         UserRepository userRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
                model.addAttribute("userId", userRepository.findByUserName(principal.getName()).getId());
            }
            model.addAttribute("allPosts", postRepository.findAll());
        }
        return "feed";
    }
}
