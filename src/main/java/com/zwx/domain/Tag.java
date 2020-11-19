package com.zwx.domain;

import com.zwx.service.TagService;
import com.zwx.service.TypeService;
import com.zwx.utils.BeanUtils;

import java.util.List;

/**
 * 博客标签类，对应数据库标签表tag
 * @author coderZWX
 * @date 2020-11-04 22:51
 */
public class Tag {

    private TagService tagService = (TagService) BeanUtils.getBean("tagServiceImp");

    private Integer id;//主键 无意义
    private String name;//标签名称

    private Integer blogNum;//该标签下博客数量

    //多对多
    private List<Blog> blogList;//打了这个标签的所有博客

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlogNum() {
        if (id!=null){
            blogNum = tagService.getBlogNum(id);
        }
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    public Tag(Integer id, String name, List<Blog> blogList) {
        this.id = id;
        this.name = name;
        this.blogList = blogList;
    }

    public Tag() {
    }
}
