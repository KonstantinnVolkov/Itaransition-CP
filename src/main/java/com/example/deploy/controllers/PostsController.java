package com.example.deploy.controllers;

import com.example.deploy.DTO.post.PostProfileDTO;
import com.example.deploy.models.Post;
import com.example.deploy.services.post.PostServiceImpl;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {

    private final PostServiceImpl postService;
    private final UserService userService;

    @Autowired
    public PostsController(PostServiceImpl postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(value = "/profile", params = "create")
    public String savePost(@ModelAttribute PostProfileDTO postForm,
                           @RequestParam("username") String username,
                           Model model) {
        model.addAttribute("postForm", postForm);
        postService.save(postForm, userService.getUserByUsername(username));
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
