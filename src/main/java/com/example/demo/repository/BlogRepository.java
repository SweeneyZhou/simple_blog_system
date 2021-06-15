package com.example.demo.repository;

import com.example.demo.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findByTypeId(Pageable pageable, Long id);

    Page<Blog> findByTagsId(Pageable pageable, Long id);

    long count();
}
