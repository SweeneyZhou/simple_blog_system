package com.example.demo.controller;

import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Comment;
import com.example.demo.service.BlogService;
import com.example.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    MyUtils myUtils;

    /**
     * 博客详情页
     */
    @GetMapping("/{id}")
    public String blog(@PathVariable Long id, Model model) {

        Blog blog = blogService.getBlog(id);
        blog.setVisitCount(blog.getVisitCount() + 1);
        blogService.saveBlog(blog);
        myUtils.changeEmail2MD5(blog);
        for (Comment c : blog.getComments()) {
            myUtils.changeEmail2MD5(c);
        }

        if (model.containsAttribute("comment")) {
            model.addAttribute("comment", model.getAttribute("comment"));
        } else {
            Comment comment = new Comment();
            comment.setBlog(blog);
            model.addAttribute("comment", comment);
        }
        model.addAttribute("blog", blog);
        return "blog/blog";
    }


}
