package com.example.demo.service;

import com.example.demo.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);

    List<Comment> getCommentsByBlogId(Long id);

    Page<Comment> pageComment(Pageable pageable);

    Comment updateComment(Long id, Comment comment);

    void deleteCommentById(Long id);

    void delete(Comment entity);

    long count();
}
