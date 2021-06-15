package com.example.demo.impl;

import com.example.demo.NotFoundException;
import com.example.demo.pojo.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        // TODO Auto-generated method stub
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        // TODO Auto-generated method stub
        return blogRepository.getOne(id);
    }


    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        // TODO Auto-generated method stub
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        // TODO Auto-generated method stub
        Blog t = blogRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("博客不存在!");
        }
        t.setType(blog.getType());
        t.setTags(blog.getTags());
        t.setContent(blog.getContent());
        return blogRepository.save(t);
    }

    @Override
    public void deleteBlog(Long id) {
        // TODO Auto-generated method stub
        Blog b = blogRepository.getOne(id);
        b.setTags(null);
        blogRepository.save(b);
        blogRepository.deleteById(id);
    }

    @Override
    public Long count() {
        // TODO Auto-generated method stub
        return blogRepository.count();
    }

    @Override
    public Page<Blog> findByTypeId(Pageable pageable, Long id) {
        // TODO Auto-generated method stub
        return blogRepository.findByTypeId(pageable, id);
    }

    @Override
    public Page<Blog> findByTagsId(Pageable pageable, Long id) {
        // TODO Auto-generated method stub
        return blogRepository.findByTagsId(pageable, id);
    }

}
