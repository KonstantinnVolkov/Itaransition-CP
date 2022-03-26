package com.example.deploy.services.comment;

public interface CommentService {

    void save(long post_id, long author_id, String commentText);
}
