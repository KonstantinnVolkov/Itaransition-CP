package com.example.deploy.controllers;

import com.example.deploy.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/saveComment")
    public String saveComment(@RequestParam(name = "author_id") long author_id,
                              @RequestParam(name = "post_id") long post_id){
//        commentService.saveComment(post_id, author_id);
        return "redirect/feed";
    }




}
