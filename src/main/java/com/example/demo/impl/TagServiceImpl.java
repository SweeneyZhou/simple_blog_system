package com.example.demo.impl;

import com.example.demo.NotFoundException;
import com.example.demo.pojo.Blog;
import com.example.demo.pojo.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BlogService blogService;

    @Override
    public Tag saveTag(Tag tag) {
        // TODO Auto-generated method stub
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        // TODO Auto-generated method stub
        return tagRepository.getOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        // TODO Auto-generated method stub
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        // TODO Auto-generated method stub
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        // TODO Auto-generated method stub
        Tag t = tagRepository.getOne(id);
        if (t == null) {
            throw new NotFoundException("标签不存在!");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        // TODO Auto-generated method stub
        Tag t = tagRepository.getOne(id);
        for (Blog b : t.getBlogs()) {
            List<Tag> tagList = b.getTags();
            tagList.remove(t);
            if (tagList.isEmpty()) {
                blogService.deleteBlog(b.getId());
            } else {
                b.setTags(tagList);
                blogService.saveBlog(b);
            }
        }
        t.setBlogs(null);
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> findAll() {
        // TODO Auto-generated method stub
        return tagRepository.findAll();
    }


}
