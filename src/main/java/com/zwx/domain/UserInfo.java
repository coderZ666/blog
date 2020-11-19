package com.zwx.domain;

import java.util.Date;
import java.util.List;

/**
 * 用户类，对应数据库用户表userInfo
 * @author coderZWX
 * @date 2020-11-04 23:04
 */
public class UserInfo {

    private Integer id;//主键 无意义
    private String nickName;//昵称
    private String username;//用户名
    private String password;//密码
    private String email;//邮箱
    private String role;//角色类型
    private String headUrl;//头像路径
    private Date creatTime;//创建时间
    private String creatTimeStr;//创建时间字符串形式
    private Date updateTime;//更新时间
    private String updateTimeStr;//更新时间字符串形式

    //一对多
    private List<Blog> blogList;//该用户发表的所有博客

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", creatTime=" + creatTime +
                ", creatTimeStr='" + creatTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", blogList=" + blogList +
                '}';
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatTimeStr() {
        return creatTimeStr;
    }

    public void setCreatTimeStr(String creatTimeStr) {
        this.creatTimeStr = creatTimeStr;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public UserInfo(Integer id, String nickName, String username, String password, String email, String role, String headUrl, Date creatTime, String creatTimeStr, Date updateTime, String updateTimeStr, List<Blog> blogList) {
        this.id = id;
        this.nickName = nickName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.headUrl = headUrl;
        this.creatTime = creatTime;
        this.creatTimeStr = creatTimeStr;
        this.updateTime = updateTime;
        this.updateTimeStr = updateTimeStr;
        this.blogList = blogList;
    }

    public UserInfo() {
    }
}
