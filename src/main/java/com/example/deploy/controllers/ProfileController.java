package com.example.deploy.controllers;

import com.example.deploy.forms.PostForm;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileController(PostService postService, PostRepository postRepository, UserRepository userRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String getProgilePage(@RequestParam("username") String username,
                                 @RequestParam("id") String user_id,
                                 Model model) {
        model.addAttribute("username", username);
        System.out.println(postRepository.findByAuthor_Id(Long.parseLong(user_id)));
        model.addAttribute("posts", postRepository.findByAuthor_Id(Long.parseLong(user_id)));
        System.out.println("hello");
        return "profile";
    }

    @PostMapping("/profile")
    public String savePost(@ModelAttribute PostForm postForm,
                           @RequestParam("username") String username,
                           Model model) {
        model.addAttribute("postForm", postForm);
        postService.save(postForm, userRepository.findByUserName(username));
        return "/feed";
    }
}
