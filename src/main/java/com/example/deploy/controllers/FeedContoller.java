package com.example.deploy.controllers;

import com.example.deploy.models.Comment;
import com.example.deploy.models.Post;
import com.example.deploy.repositories.CommentRepository;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class FeedContoller {

    private final UserRepository userRepository;
    private final PostService postService;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public FeedContoller(PostService postService, PostRepository postRepository,
                         UserRepository userRepository, CommentRepository commentRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    //Post has to keep comments, not comment keep post
    //Rewrite posts and comments DB

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
        }
        List<Post> allPosts = postRepository.findAllByOrderByPostedDesc();
        model.addAttribute("allPosts", allPosts);
        return "feed";
    }
}
