package com.example.demo.controller;

import com.example.demo.pojo.Message;
import com.example.demo.service.MessageService;
import com.example.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Iterator;

@Controller
@RequestMapping("/message")
public class MessageBoardIndexController {

    @Autowired
    MessageService messageService;
    @Autowired
    MyUtils myUtils;

    @GetMapping()
    public String index(@PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, Model model) {
        Page<Message> page = messageService.pageMessage(pageable);
        for (Iterator<Message> iterator = page.getContent().iterator(); iterator.hasNext(); ) {
            Message m = (Message) iterator.next();
            myUtils.changeEmail2MD5(m);
        }

        if (!model.containsAttribute("message")) {

            Message message = new Message();
            model.addAttribute("message", message);
        }
        model.addAttribute("page", page);
        return "message/message";
    }

    @PostMapping("/send")
    public String post(@Valid Message message, BindingResult bindingResult, Model model, @PageableDefault(size = 5, sort = {"id"}, direction = Direction.ASC) Pageable pageable, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.message", bindingResult);
            attr.addFlashAttribute("message", message);
        } else {
            messageService.save(message);
        }
        return "redirect:/message";
    }
}
