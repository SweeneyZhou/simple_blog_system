package com.example.demo.controller;

import com.example.demo.pojo.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentIndexController {
    @Autowired
    CommentService commentService;

    @PostMapping()
    public String add(@Valid Comment comment, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.comment", bindingResult);
            attr.addFlashAttribute("comment", comment);
        } else {
            commentService.save(comment);
        }
        return "redirect:/blog/" + comment.getBlog().getId();
    }


}
