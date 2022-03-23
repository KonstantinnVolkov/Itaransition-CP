package com.example.deploy.controllers;

import com.example.deploy.forms.PostForm;
import com.example.deploy.models.Role;
import com.example.deploy.models.User;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
                                 Model model,
                                 Principal principal) {
        model.addAttribute("username", username);
        model.addAttribute("id", user_id);
        User profileUser = userRepository.findByUserName(username);    //Забираем пользователя профиля
        User currentUser = userRepository.findByUserName(principal.getName());  //Забираем текущего залогиненого пользователя
        if ( currentUser.getRole().equals(Role.ADMIN) ||        //Может ли пользователь редактировать и создавать свои и чужие посты
                (currentUser.getUserName().equals(username) && String.valueOf(currentUser.getId()).equals(user_id)) ) {
            model.addAttribute("isEditEnabled", true);
        }
        else {
            model.addAttribute("isEditEnabled", false);
        }
        model.addAttribute("posts", postRepository.findAllByAuthor_Id(Long.parseLong(user_id)));
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
