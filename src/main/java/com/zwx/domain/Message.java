package com.zwx.domain;

import com.zwx.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * 留言类，与留言表对应
 * @author coderZWX
 * @date 2020-11-10 21:03
 */
public class Message {

    private Integer id;//主键 无意义
    private Integer parentId;//自关联外键 表示我在回复哪个楼层
    private Integer role;//评论人身份（0 游客，1 博主）
    private String nickname;//昵称
    private String parentNickname;//回复人的昵称即 @谁
    private String email;//邮箱
    private String headUrl;//头像路径
    private String content;//评论内容
    private String sensitiveWord;//敏感词
    private Date creatTime;//评论时间
    private String creatTimeStr;//评论时间的字符串表示

    //一对多，所有子评论（自关联依赖）
    private List<Comment> comments;//所有子评论

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getParentNickname() {
        return parentNickname;
    }

    public void setParentNickname(String parentNickname) {
        this.parentNickname = parentNickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatTimeStr() {
        if(creatTime!=null){
            creatTimeStr = DateUtils.dateToString(creatTime);
        }
        return creatTimeStr;
    }

    public void setCreatTimeStr(String creatTimeStr) {
        this.creatTimeStr = creatTimeStr;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", role=" + role +
                ", nickname='" + nickname + '\'' +
                ", parentNickname='" + parentNickname + '\'' +
                ", email='" + email + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", content='" + content + '\'' +
                ", sensitiveWord='" + sensitiveWord + '\'' +
                ", creatTime=" + creatTime +
                ", creatTimeStr='" + creatTimeStr + '\'' +
                ", comments=" + comments +
                '}';
    }
}
