package com.example.demo.controller;

import com.example.demo.pojo.Blog;
import com.example.demo.service.BlogService;
import com.example.demo.service.UserService;
import com.example.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    MyUtils myUtils;

    @GetMapping({"", "index"})
    public String Hello(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {
        // TODO Auto-generated method stub
        Page<Blog> page = blogService.listBlog(pageable);
        for (Blog t : page.getContent()) {
            myUtils.changeEmail2MD5(t);
        }
        model.addAttribute("page", page);

        return "index";
    }

    @GetMapping("login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/admin";
        } else
            return "login";
    }

    @GetMapping("about")
    public String about(Model model) {
        model.addAttribute("user", userService.findById(1l));
        return "about";
    }


}
