package com.example.deploy.controllers;

import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import com.example.deploy.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
public class FeedContoller {

    private final UserDetailsServiceImpl userDetailService;
    private final UserRepository userRepository;
    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public FeedContoller(UserDetailsServiceImpl userDetailService, PostService postService, PostRepository postRepository, UserRepository userRepository) {
        this.userDetailService = userDetailService;
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feed")
    public String getFeedPage(Model model, Principal principal, Authentication authentication) {
        boolean isAuthenticated;
        if (authentication != null) {
            isAuthenticated = authentication.isAuthenticated();
            if (isAuthenticated) {
                model.addAttribute("authentication", isAuthenticated);
                model.addAttribute("username", principal.getName());
                model.addAttribute("userId", userRepository.findByUserName(principal.getName()).getId());
            }
        }
        model.addAttribute("allPosts", postRepository.findAll());
        return "feed";
    }
}
