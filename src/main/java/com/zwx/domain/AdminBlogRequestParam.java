package com.zwx.domain;

/**
 * 管理员查看博客有可能的全部请求参数
 * @author coderZWX
 * @date 2020-11-06 13:27
 */
public class AdminBlogRequestParam {

    private String title;//标题
    private Integer typeId;//类型
    private boolean recommend;//是否推荐

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public AdminBlogRequestParam(String title, Integer typeId, boolean recommend) {
        this.title = title;
        this.typeId = typeId;
        this.recommend = recommend;
    }

    public AdminBlogRequestParam() {
    }
}
