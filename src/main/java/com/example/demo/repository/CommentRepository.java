package com.example.demo.repository;

import com.example.demo.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogId(Long id);

    Long countByBlogId(Long id);

    long count();
}
