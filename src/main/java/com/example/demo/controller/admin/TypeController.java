package com.example.demo.controller.admin;

import com.example.demo.pojo.Type;
import com.example.demo.service.TypeService;
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
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String toTypePage(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {

        model.addAttribute("page", typeService.listType(pageable));
        return "admin/type/list";
    }

    @GetMapping("/type/add")
    public String toAddPage(Model model) {
        model.addAttribute(new Type());
        return "admin/type/input";
    }

    @GetMapping("/type/{id}")
    public String toEditPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/type/input";
    }


    @PostMapping("/type")
    public String addType(@Valid Type type, BindingResult bindingResult) {
        if (typeService.getTypeByName(type.getName()) != null) {
            bindingResult.rejectValue("name", "该分类名已存在", "该分类已存在");
        }
        if (bindingResult.hasErrors()) {
            return "admin/type/input";
        }
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    @PutMapping("/type/{id}")
    public String editType(@PathVariable("id") Long id, @Valid Type type, BindingResult bindingResult) {
        if (typeService.getTypeByName(type.getName()) != null) {
            bindingResult.rejectValue("name", "该用户名已存在", "该用户名已存在");
        }
        if (bindingResult.hasErrors()) {
            return "admin/type/input";
        }
        typeService.updateType(id, type);
        return "redirect:/admin/types";
    }

    @DeleteMapping("/type/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
