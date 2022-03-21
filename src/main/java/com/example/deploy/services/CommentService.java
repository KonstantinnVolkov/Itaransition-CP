package com.example.deploy.services;

import com.example.deploy.forms.CommentForm;
import com.example.deploy.models.Comment;
import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import com.example.deploy.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(CommentForm commentForm, Post post, User author) {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setPost(post);
        comment.setMessage(commentForm.getMessage());
        commentRepository.save(comment);
    }


}
