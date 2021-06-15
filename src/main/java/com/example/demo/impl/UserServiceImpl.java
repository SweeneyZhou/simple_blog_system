package com.example.demo.impl;

import com.example.demo.NotFoundException;
import com.example.demo.pojo.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    String PREFIX = "ROLE_";

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {

        User temp = userRepository.getOne(id);
        if (temp == null) {
            throw new NotFoundException();
        }
        BeanUtils.copyProperties(user, temp, "password", "roles");
        return userRepository.save(temp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        } else {
            //新建一个瞬时态的user对象临时承接数据
            User tempUesr = new User();
            BeanUtils.copyProperties(user, tempUesr);
            //数据加密
            tempUesr.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return tempUesr;
        }
    }

}
