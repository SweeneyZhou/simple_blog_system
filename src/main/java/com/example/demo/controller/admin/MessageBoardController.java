package com.example.demo.controller.admin;

import com.example.demo.pojo.Message;
import com.example.demo.pojo.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.example.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin/message")
public class MessageBoardController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    MyUtils myUtils;

    @GetMapping()
    public String index(@PageableDefault(size = 10, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {
        Page<Message> page = messageService.pageMessage(pageable);

        for (Message m : page.getContent()) {
            String email = m.getEmail();
            m.setEmail(DigestUtils.md5DigestAsHex(email.getBytes()));
        }
        if (!model.containsAttribute("form")) {
            Message messageForm = new Message();
            model.addAttribute("form", messageForm);
        } else {
            System.out.println(1);
            model.addAttribute("form", model.getAttribute("form"));
        }

        model.addAttribute("page", page);
        return "admin/message/list";
    }

    @PostMapping()
    public String reply(Message message, Principal principal, RedirectAttributes attr) {

        User user = userService.findByUsername(principal.getName());
        message.setEmail(user.getEmail());
        message.setNickname(user.getNickname());
        messageService.save(message);
        return "redirect:/admin/message";
    }
}
