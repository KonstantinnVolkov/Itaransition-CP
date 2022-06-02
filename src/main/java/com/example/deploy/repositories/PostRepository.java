package com.example.deploy.repositories;

import com.example.deploy.models.Post;
import com.example.deploy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Post set tags = :tags, theme = :theme, body = :body where post_id = :post_id")
    void updatePostById(@Param("post_id") long post_id, @Param("tags") String tags,
                        @Param("theme") String theme, @Param("body") String body);

    List<Post> findAllByAuthor_IdOrderByPostedDesc(Long author_id);
    List<Post> findAllByOrderByPostedDesc();
    Post findPostByAuthorAndAndBody(User author, String body);
    List<Post> findAllByTagsOrderByPostedDesc(String tags);
}