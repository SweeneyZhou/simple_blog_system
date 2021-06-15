package com.example.demo.impl;

import com.example.demo.NotFoundException;
import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private BlogService blogService;

    @Override
    public Type saveType(Type type) {
        // TODO Auto-generated method stub
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        // TODO Auto-generated method stub
        return typeRepository.getOne(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        // TODO Auto-generated method stub
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {
        // TODO Auto-generated method stub
        Type t = typeRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("类型ID不存在!");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Override
    public void deleteType(Long id) {
        // TODO Auto-generated method stub
        Type t = typeRepository.getOne(id);
        List<Blog> blogs = t.getBlogs();
        for (Blog b : blogs) {
            blogService.deleteBlog(b.getId());
        }
        typeRepository.deleteById(id);

    }

    @Override
    public Type getTypeByName(String name) {
        // TODO Auto-generated method stub
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> findAll() {
        // TODO Auto-generated method stub
        return typeRepository.findAll();
    }


}
