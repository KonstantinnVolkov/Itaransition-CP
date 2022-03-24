package com.example.deploy.controllers;

import com.example.deploy.forms.PostForm;
import com.example.deploy.models.Post;
import com.example.deploy.repositories.PostRepository;
import com.example.deploy.repositories.UserRepository;
import com.example.deploy.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {

    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostsController(PostService postService, PostRepository postRepository, UserRepository userRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/profile", params = "create")
    public String savePost(@ModelAttribute PostForm postForm,
                           @RequestParam("username") String username,
                           Model model) {
        model.addAttribute("postForm", postForm);
        postService.save(postForm, userRepository.findByUserName(username));
        return "redirect:/feed";
    }

    @GetMapping("/postEditor")
    private String getPostEditorPage(@RequestParam("id") long authorId,
                                     @RequestParam("username") String username,
                                     @RequestParam("postId") long postId,
                                     Model model){
        Post post = postService.getPostById(postId);
        model.addAttribute("postToEdit", post);
        return "postEdit";
    }

    @PostMapping("/postEditor")
    private String saveUpdatedPost(@ModelAttribute Post editedPost){
        postService.updatePost(editedPost);

        return "redirect:/feed";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam("id") long authorId,
                             @RequestParam("username") String username,
                             @RequestParam("postId") long postId){
        postService.delete(postId);
        return "redirect:/feed";
    }
}
