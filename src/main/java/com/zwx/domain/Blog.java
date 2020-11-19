package com.zwx.domain;

import com.zwx.service.CommentService;
import com.zwx.utils.BeanUtils;
import com.zwx.utils.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * 博客类，对应数据库博客表Blog
 * @author coderZWX
 * @date 2020-11-04 22:21
 */
public class Blog {

    private CommentService commentService = (CommentService) BeanUtils.getBean("commentServiceImp");

    private Integer id;//主键id 无意义
    private String title;//标题
    private String content;//内容
    private String firstPicture;//首图
    private String flag;//标记（原创、转账或翻译）
    private String description;//博客简介，用于首页展示
    private String music;//外链音乐链接
    private Integer views;//浏览量
    private Integer commentNum;//评论量
    private boolean appreciation;//是否开启赞赏
    private boolean copyright;//是否开启版权
    private boolean commentAble;//是否开启评论
    private boolean recommend;//是否推荐
    private Integer status;//状态
    private String statusStr;//状态的字符串描述  0表示已发布  1表示暂存为草稿
    private Date creatTime;//发布时间
    private String creatTimeStr;//发布时间字符串形式
    private Date updateTime;//修改时间
    private String updateTimeStr;//修改时间字符串形式

    private String tagIds;//逗号隔开的标签id字符串

    //多对一外键依赖
    private UserInfo userInfo;//外键 所属用户
    private Type type;//外键 所属分类

    //多对多
    private List<Tag> tags;//该博客带有的所有标签

    //一对多
    private List<Comment> comments;//该博客下的所有评论

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public boolean isCommentAble() {
        return commentAble;
    }

    public void setCommentAble(boolean commentAble) {
        this.commentAble = commentAble;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status!=null){
            if (status == 0){
                statusStr = "已发布";
            }else if (status == 1){
                statusStr = "草稿";
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public Integer getCommentNum() {
        if (id!=null){
            commentNum = commentService.countCommentsByBlogId(id);
        }
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatTimeStr() {
        if (creatTime!=null){
            creatTimeStr = DateUtils.dateToString(creatTime);
        }
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
        if (updateTime!=null){
            updateTimeStr = DateUtils.dateToString(updateTime);
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getTagIds() {
        if (tags!=null){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            tagIds = ids.toString();
        }
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Blog(Integer id, String title, String content, String firstPicture, String flag, String description, Integer views, boolean appreciation, boolean copyright, boolean commentAble, boolean recommend, Integer status, String statusStr, Date createTime, String creatTimeStr, Date updateTime, String updateTimeStr, String tagIds, UserInfo userInfo, Type type, List<Tag> tags, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.flag = flag;
        this.description = description;
        this.views = views;
        this.appreciation = appreciation;
        this.copyright = copyright;
        this.commentAble = commentAble;
        this.recommend = recommend;
        this.status = status;
        this.statusStr = statusStr;
        this.creatTime = createTime;
        this.creatTimeStr = creatTimeStr;
        this.updateTime = updateTime;
        this.updateTimeStr = updateTimeStr;
        this.tagIds = tagIds;
        this.userInfo = userInfo;
        this.type = type;
        this.tags = tags;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", copyright=" + copyright +
                ", commentAble=" + commentAble +
                ", recommend=" + recommend +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", creatTime=" + creatTime +
                ", creatTimeStr='" + creatTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", tagIds='" + tagIds + '\'' +
                ", userInfo=" + userInfo +
                ", type=" + type +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }

    public Blog() {
    }
}
