package com.example.deploy.mappers;

import com.example.deploy.models.Comment;
import com.example.deploy.models.User;

public class CommentMapper {

    public static Comment mapDTO_toEntity(User author, long post_id, String commentText){
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setPost_id(post_id);
        comment.setComment(commentText);
        return comment;
    }

}
