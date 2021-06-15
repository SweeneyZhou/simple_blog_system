package com.example.demo.controller.api;

import com.example.demo.service.BlogService;
import com.example.demo.service.CommentService;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/count")
public class CountController {

    @Autowired
    MessageService messageService;
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    @GetMapping("/message")
    public long message() {
        return messageService.count();
    }

    @GetMapping("/blog")
    public long blog() {
        return blogService.count();
    }

    @GetMapping("/comment")
    public long comment() {
        return commentService.count();
    }
}
