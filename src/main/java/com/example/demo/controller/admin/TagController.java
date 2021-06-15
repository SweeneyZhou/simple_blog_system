package com.example.demo.controller.admin;

import com.example.demo.pojo.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public String toTagPage(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {

        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tag/list";
    }

    @GetMapping("/tag/add")
    public String toAddPage(Model model) {
        model.addAttribute(new Tag());
        return "admin/tag/input";
    }

    @GetMapping("/tag/{id}")
    public String toEditPage(Model model, @PathVariable("id") Long id) {

        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag/input";
    }


    @PostMapping("/tag")
    public String addTag(@Valid Tag tag, BindingResult bindingResult) {
        if (tagService.getTagByName(tag.getName()) != null) {
            bindingResult.rejectValue("name", "该标签名已存在", "该标签名已存在");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tag/input";
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    @PutMapping("/tag/{id}")
    public String editTag(@PathVariable("id") Long id, @Valid Tag tag, BindingResult bindingResult) {
        if (tagService.getTagByName(tag.getName()) != null) {
            bindingResult.rejectValue("name", "该标签名已存在", "该标签名已存在");
        }
        if (bindingResult.hasErrors()) {
            return "admin/tag/input";
        }
        tagService.updateTag(id, tag);
        return "redirect:/admin/tags";
    }

    @DeleteMapping("/tag/{id}")
    public String deleteTag(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
