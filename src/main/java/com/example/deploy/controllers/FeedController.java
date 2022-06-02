package com.example.deploy.controllers;

import com.example.deploy.mappers.PostMapper;
import com.example.deploy.models.Post;
import com.example.deploy.services.post.PostServiceImpl;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequestMapping("/feed")
@Controller
public class FeedController {

    private final PostServiceImpl postService;
    private final UserService userService;

    @Autowired
    public FeedController(PostServiceImpl postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String getFeedPage(@RequestParam(required = false, value = "sortedBy") String sortedBy,
                                            Model model, Principal principal,
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
        if (sortedBy == null) {
            System.out.println("sortedBy is null");
            model.addAttribute("allPosts", PostMapper.mapEntityToFeedDTO(postService.getPostByDateDesc()));
        }
        else {
            List<Post> sortedPosts = postService.getPostsSortedByTag(sortedBy);
            if (sortedPosts.isEmpty()) {
                return "redirect:/feed";
            }
            model.addAttribute("allPosts", PostMapper.mapEntityToFeedDTO(sortedPosts));
            System.out.println("sortedBy is " + sortedBy);
        }
        return "feed";
    }
}