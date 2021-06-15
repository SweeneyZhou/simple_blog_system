package com.example.demo.service;

import com.example.demo.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Long count();

    Page<Blog> findByTypeId(Pageable pageable, Long id);

    Page<Blog> findByTagsId(Pageable pageable, Long id);
}
