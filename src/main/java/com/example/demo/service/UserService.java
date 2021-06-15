package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {

    User findByUsername(String username);

    User save(User user);

    User findById(Long id);

    User update(Long id, User user);

}
