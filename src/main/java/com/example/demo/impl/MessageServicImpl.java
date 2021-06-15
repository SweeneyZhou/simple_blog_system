package com.example.demo.impl;

import com.example.demo.pojo.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageServicImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;


    @Override
    public Page<Message> pageMessage(Pageable pageable) {
        return messageRepository.findAll(pageable);

    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public long count() {
        return messageRepository.count();
    }
}
