package com.example.demo.service;

import com.example.demo.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> findAll();

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
