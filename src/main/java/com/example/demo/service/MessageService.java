package com.example.demo.service;

import com.example.demo.pojo.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    Page<Message> pageMessage(Pageable pageable);

    Message save(Message message);

    long count();

}
