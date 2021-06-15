package com.example.demo.controller.admin;

import com.example.demo.pojo.ChangePasswordVO;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService = null;

    @GetMapping()
    public String index(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        User userInfo = userService.findById(user.getId());
        model.addAttribute("userInfo", userInfo);
        return "admin/index";
    }

    @PostMapping
    public String post(@Valid User user, BindingResult bindingResult, RedirectAttributes attr) throws ParseException {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.userinfo", bindingResult);
            attr.addFlashAttribute("userinfo", user);
            return "redirect:/admin/edit/" + user.getId();
        }
        userService.update(user.getId(), user);
        attr.addFlashAttribute("successMsg", "修改成功！");
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable Long id, Model model) {

        if (model.containsAttribute("userinfo")) {

        } else {
            User user = userService.findById(id);
            model.addAttribute("userinfo", user);
        }
        return "admin/input";
    }

    @GetMapping("/password/{id}")
    public String toEditPasswordPage(Model model, @PathVariable("id") Long id) {
        ChangePasswordVO vo = new ChangePasswordVO();
        vo.setUserId(id);
        model.addAttribute("changePassword", vo);
        return "admin/input";
    }

    @PutMapping
    public String editPassword(@Valid ChangePasswordVO vo, BindingResult bindingResult, RedirectAttributes attr) {
        User user = userService.findById(vo.getUserId());
        if (!user.getPassword().equals(vo.getOldPassword())) {
            bindingResult.rejectValue("newPassword", "403", "原密码错误！");
        }
        if (bindingResult.hasErrors()) {
            return "admin/input";
        }
        user.setPassword(vo.getNewPassword());
        userService.save(user);
        attr.addFlashAttribute("successMsg", "修改成功！");
        return "redirect:/admin";
    }
}
