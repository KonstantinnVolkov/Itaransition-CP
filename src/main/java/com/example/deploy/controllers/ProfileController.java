package com.example.deploy.controllers;

import com.example.deploy.forms.PostForm;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/profile{id}{username}")
    public String getProgilePage(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String user_id = request.getParameter("id");
        model.addAttribute("username", username);
//        model.addAttribute("posts", postRepository.findByAuthor(Long.parseLong(user_id)));
        return "profile";
    }

    @PostMapping("/profile{id}{username}")
    public String savePost(@ModelAttribute PostForm postForm, HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        model.addAttribute("postForm", postForm);
        postService.save(postForm, userRepository.findByUserName(username));
        return "/feed";
    }
}
