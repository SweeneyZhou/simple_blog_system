package com.example.demo.impl;

import com.example.demo.NotFoundException;
import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BlogService blogService;

    @Override
    public Comment save(Comment comment) {
        // TODO Auto-generated method stub
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByBlogId(Long id) {
        // TODO Auto-generated method stub
        return commentRepository.findByBlogId(id);
    }

    @Override
    public Page<Comment> pageComment(Pageable pageable) {
        // TODO Auto-generated method stub
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        // TODO Auto-generated method stub
        Comment temp = commentRepository.getOne(id);
        if (temp != null) {
            temp.setContent(comment.getContent());
        } else {
            throw new NotFoundException("Comment not found!");
        }
        return commentRepository.save(temp);
    }

    @Override
    public void deleteCommentById(Long id) {
        // TODO Auto-generated method stub

        Comment temp = commentRepository.getOne(id);
        Blog b = temp.getBlog();
        b.getComments().remove(temp);
        blogService.saveBlog(b);
        if (temp.getReplyComments() != null && temp.getReplyComments().size() > 0) {
            for (Comment c : temp.getReplyComments()) {
                delete(c);
            }
        }

        commentRepository.deleteById(id);
    }

    @Override
    public void delete(Comment entity) {
        // TODO Auto-generated method stub
        Blog b = entity.getBlog();
        b.getComments().remove(entity);
        blogService.saveBlog(b);

        if (entity.getReplyComments() != null && entity.getReplyComments().size() > 0) {
            for (Comment c : entity.getReplyComments()) {
                delete(c);
            }
        }
        commentRepository.delete(entity);

    }

    @Override
    public long count() {
        return commentRepository.count();
    }


}
