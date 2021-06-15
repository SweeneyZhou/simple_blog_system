package com.example.demo.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    //标签样式
    private String style;

    private List<Blog> blogs = new ArrayList<>();

    public TagVO() {
        // TODO Auto-generated constructor stub
    }

    public TagVO(Tag tag) {
        // TODO Auto-generated constructor stub
        this.id = tag.getId();
        this.name = tag.getName();
        this.style = "<span class='badge badge-success'>" + tag.getName() + "</span>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
