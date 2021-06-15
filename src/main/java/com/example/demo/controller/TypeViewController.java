package com.example.demo.controller;

import com.example.demo.service.BlogService;
import com.example.demo.service.TypeService;
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
@RequestMapping("/type")
public class TypeViewController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("list", typeService.findAll());
        return "type/view";
    }

    @PostMapping()
    public String getBlogByTypeId(Long id, Model model, @PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable) {
        System.out.println("类型id" + id);
        model.addAttribute("page", blogService.findByTypeId(pageable, id));
        return "index::blog";
    }
}
