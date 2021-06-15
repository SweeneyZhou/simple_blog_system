package com.example.demo.utils;

import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Message;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class MyUtils {

    public void changeEmail2MD5(Blog blog) {
        if (blog.getUser() != null) {
            String email = blog.getUser().getEmail();
            if (email.contains("@"))
                blog.getUser().setEmail(DigestUtils.md5DigestAsHex(blog.getUser().getEmail().getBytes()));
        }
    }

    public void changeEmail2MD5(Comment comment) {
        String email = comment.getEmail();
        comment.setEmail(DigestUtils.md5DigestAsHex(email.getBytes()));
    }

    public void changeEmail2MD5(Message message) {
        String email = message.getEmail();
        message.setEmail(DigestUtils.md5DigestAsHex(email.getBytes()));
    }

}
