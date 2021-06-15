package com.example.demo.controller.admin;

import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comments")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", commentService.pageComment(pageable));
        return "admin/comment/list";
    }

    @DeleteMapping("/comment/{id}")
    public String delete(@PathVariable("id") Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/admin/comments";
    }

}
