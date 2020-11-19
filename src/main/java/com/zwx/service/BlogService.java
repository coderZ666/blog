package com.zwx.service;

import com.zwx.domain.AdminBlogRequestParam;
import com.zwx.domain.Blog;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 有关博客操作的服务层
 * @author coderZWX
 * @date 2020-11-06 13:41
 */
@Transactional
public interface BlogService {

    /**
     * 根据动态查询数据查询博客信息并分页
     * @param abrp 封装动态查询信息的对象
     * @param page 当前页
     * @param size 每页展示的博客条数
     * @return 返回查到的博客集合
     */
    List<Blog> listBlogByDynParam(AdminBlogRequestParam abrp,Integer page,Integer size);

    /**
     * 查询全部博客信息并分页
     * @param page 当前页
     * @param size 每页展示条数
     * @return 返回查到的博客集合
     */
    List<Blog> listBlog(Integer page,Integer size);

    /**
     * 查询全部已发布（不含草稿）博客信息(含所有评论)并分页
     * @param page 当前页
     * @param size 每页展示条数
     * @return 返回查到的博客集合
     */
    List<Blog> listBlogAll(Integer page,Integer size);

    /**
     * 插入一条新的博客
     * @param blog 封装新博客信息的对象
     * @return 返回插入的条数
     */
    int saveBlog(Blog blog);

    /**
     * 根据id查询博客
     * @param id 博客id
     * @return 查到的博客对象
     */
    Blog getBlogById(Integer id);

    /**
     * 根据标题查询博客id
     * @return 查到的博客id
     */
    Integer getBlogIdByTitle(String title);

    /**
     * 修改博客
     * @param blog 封装修改后博客信息的对象
     * @return 修改成功的博客数
     */
    Integer updateBlog(Blog blog);

    /**
     * 根据博客id删除一个博客，以及所有依赖
     * @param blogId 博客id
     * @return 删除的条数
     */
    Integer deleteBlog(Integer blogId);

    /**
     * 查询四条最新推荐博客
     * @return 四条最新推荐博客的集合
     */
    List<Blog> getRecommend4Blog();

    /**
     * 根据搜索内容字符串，在博客标题和内容中模糊查询
     * @param query 查询内容字符串
     * @return 查到的博客集合
     */
    List<Blog> listBlogQuery(Integer page,Integer size,String query);

    /**
     * 根据博客id查询博客详细信息（包含所有评论信息）
     * @param id 博客id
     * @return 封装详细博客信息的对象
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
     * 根据分类id查询分类下的所有已发布博客，并分页
     * @param page 查第几页
     * @param size 每页几条
     * @param id 分类id
     * @return 返回查到的博客集合
     */
    List<Blog> listBlogByTypeId(Integer page, Integer size, Integer id);

    /**
     * 根据标签id查询标签下的所有已发布博客，并分页
     * @param page 查第几页
     * @param size 每页几条
     * @param id 标签id
     * @return 返回查到的博客集合
     */
    List<Blog> listBlogByTagId(Integer page, Integer size, Integer id);
}
