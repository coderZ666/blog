package com.zwx.dao;

import com.zwx.domain.AdminBlogRequestParam;
import com.zwx.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作博客表的数据访问层
 * @author coderZWX
 * @date 2020-11-06 13:35
 */
@Mapper
public interface BlogDao {

    /**
     * 根据参数动态查询相应条件下的博客对象集合
     * @param abrp 封装查询参数的对象
     * @return 查到的博客集合
     */
    List<Blog> listBlogByDynParam(AdminBlogRequestParam abrp);

    /**
     * 查询所有的博客
     * @return 博客集合对象
     */
    List<Blog> listBlog();

    /**
     * 查询所有已发布(不含草稿)的博客(含评论)
     * @return 博客集合对象
     */
    List<Blog> listBlogAll();

    /**
     * 向数据库插入一条新的博客信息
     * @param blog 封装新博客信息的对象
     * @return 返回插入成功的条数
     */
    int saveBlog(Blog blog);

    /**
     * 根据标题查询博客id
     * @param title 博客标题
     * @return 博客id
     */
    Integer getBlogIdByTitle(String title);

    /**
     * 在博客标签中间表中插入所有该博客对应的标签信息
     * @param blogId 博客id
     * @param tagId 标签id
     * @return 返回成功插入的条数
     */
    int saveBlogManyToMany(@Param("blogId") Integer blogId,@Param("tagId") Integer tagId);

    /**
     * 根据id查询博客
     * @param id 博客id
     * @return 查到的博客对象
     */
    Blog getBlogById(Integer id);

    /**
     * 修改博客
     * @param blog 封装修改后博客信息的对象
     * @return 修改成功的博客数
     */
    Integer updateBlog(Blog blog);

    /**
     * 在博客标签中间表中删除该id对应博客对应的所有标签信息
     * @param blogId 博客id
     */
    void deleteBlogManyToMany(Integer blogId);

    /**
     * 根据id删除一条博客
     * @param blogId 博客id
     * @return 删除的条数
     */
    Integer deleteBlogById(Integer blogId);

    /**
     * 查询四条最新推荐的博客，只要id、首图地址和标题
     * @return 返回查到的四条博客
     */
    List<Blog> getRecommend4Blog();

    /**
     * 根据搜索内容字符串，在博客标题和内容中模糊查询
     * @param query 查询内容字符串
     * @return 查到的博客集合
     */
    List<Blog> listBlogQuery(String query);

    /**
     * 根据博客id查询博客详细信息
     * @param id 博客id
     * @return 封装博客详细信息的博客对象
     */
    Blog getBlogDetailById(Integer id);

    /**
     * 查询博客总数
     * @return 博客总数
     */
    Integer getBlogTotal();

    /**
     * 查询博客访问量总数
     * @return 访问量总数
     */
    Integer getBlogViewsTotal();

    /**
     * 查询博客评论总数
     * @return 评论总数
     */
    Integer getBlogCommentTotal();

    /**
     * 访问博客的时候调用，浏览量加一
     * @param blogId 博客id
     */
    void viewsAdd1(Integer blogId);

    /**
     * 根据分类id查询该分类下所有已发布博客的集合
     * @param id 分类id
     * @return 查到的博客集合
     */
    List<Blog> listBlogByTypeId(Integer id);

    /**
     * 根据标签id查询该标签下所有已发布博客的集合
     * @param id 标签
     * @return 查到的博客集合
     */
    List<Blog> listBlogByTagId(Integer id);
}
