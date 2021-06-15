package com.example.demo.controller.admin;

import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Tag;
import com.example.demo.pojo.TagVO;
import com.example.demo.pojo.User;
import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import com.example.demo.service.TypeService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogControllor {
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

    /**
     * list页面
     *
     * @param id
     * @return
     */
    @GetMapping("/blogs")
    public String toListPage(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {

        model.addAttribute("page", blogService.listBlog(pageable));
        return "admin/blog/list";
    }

    /**
     * 新增页面
     *
     * @param id
     * @return
     */
    @GetMapping("/blog/add")
    public String toAddPage(Model model, Principal principal) {
        Blog temp = new Blog();
        temp.setContent("[TOC]");
        model.addAttribute("types", typeService.findAll());

        model.addAttribute("taglist", getTagVOList());
        model.addAttribute(temp);

        User user = userService.findByUsername(principal.getName());
        temp.setUser(user);

        return "admin/blog/input";
    }

    /**
     * 新增
     *
     * @param id
     * @return
     */
    @PostMapping("/blog")
    public String add(@Valid Blog blog, BindingResult bindingResult, Principal principal, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("taglist", getTagVOList());
            return "admin/blog/input";
        }

        blogService.saveBlog(blog);
        return "redirect:/admin/blogs";
    }

    /**
     * 修改页面
     *
     * @param id
     * @return
     */
    @GetMapping("/blog/{id}")
    public String toEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("taglist", getTagVOList());
        model.addAttribute(blogService.getBlog(id));
        return "admin/blog/input";
    }

    /**
     * 修改
     *
     * @param id
     * @return
     */
    @PutMapping("/blog/{id}")
    public String put(@PathVariable("id") Long id, @Valid Blog blog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/blog/input";
        }
        blogService.updateBlog(id, blog);
        return "redirect:/admin/blogs";
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/blog/{id}")
    public String delete(@PathVariable("id") Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }


    public List<TagVO> getTagVOList() {
        List<Tag> tags = tagService.findAll();
        List<TagVO> tagVOList = new ArrayList<TagVO>(tags.size());
        for (Tag t : tags) {
            tagVOList.add(new TagVO(t));
        }
        return tagVOList;

    }
}
