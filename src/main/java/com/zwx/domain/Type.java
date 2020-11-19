package com.zwx.domain;

import com.zwx.service.TypeService;
import com.zwx.utils.BeanUtils;

import java.util.List;

/**
 * 博客分类，对应分类表Type
 * @author coderZWX
 * @date 2020-11-04 22:44
 */
public class Type {

    private TypeService typeService = (TypeService) BeanUtils.getBean("typeServiceImp");

    private Integer id;//主键 无意义
    private String name;//分类名称

    private Integer blogNum;//该分类下博客数量

    //一对多
    private List<Blog> blogList;//该分类下的所有博客

    public Integer getBlogNum() {
        if (id!=null){
            blogNum = typeService.getBlogNum(id);
        }
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

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

    public Type(Integer id, String name, List<Blog> blogList) {
        this.id = id;
        this.name = name;
        this.blogList = blogList;
    }

    public Type() {
    }
}
