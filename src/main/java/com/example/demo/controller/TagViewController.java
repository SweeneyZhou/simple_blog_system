package com.example.demo.controller;

import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagViewController {
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("list", tagService.findAll());
        return "tag/view";
    }

    @PostMapping
    public String getBlogByTypeId(Long id, Model model, @PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
        System.out.println("类型id" + id);
        model.addAttribute("page", blogService.findByTagsId(pageable, id));
        return "index::blog";
    }
}
