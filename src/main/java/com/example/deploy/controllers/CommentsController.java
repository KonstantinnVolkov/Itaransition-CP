package com.example.deploy.controllers;

import com.example.deploy.DTO.comment.CommentDTO;
import com.example.deploy.services.comment.CommentService;
import com.example.deploy.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CommentsController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentsController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/saveComment")
    public String saveComment(@RequestParam("post_id") long post_id,
                              CommentDTO commentToSave, Principal principal) {
        if (!commentToSave.getCommentText().isEmpty()) {
            commentService.save(post_id, userService.getUserByUsername(principal.getName()).getId(), commentToSave.getCommentText());
        }
        return "redirect:/feed";
    }

}
