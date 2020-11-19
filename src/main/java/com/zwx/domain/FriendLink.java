package com.zwx.domain;

import com.zwx.utils.DateUtils;

import java.util.Date;

/**
 * 友链类，对应数据库friendLink表
 * @author coderZWX
 * @date 2020-11-11 16:01
 */
public class FriendLink {

    private Integer id;//主键 无意义
    private String blogName;// 博客名称
    private String blogAddress;// 博客地址
    private String pictureAddress;// 图片地址
    private Date createTime;//创建时间
    private String createTimeStr;//创建时间字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        if (createTime!=null){
            createTimeStr = DateUtils.dateToString(createTime);
        }
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
