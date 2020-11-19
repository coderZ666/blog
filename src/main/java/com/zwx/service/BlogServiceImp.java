package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.BlogDao;
import com.zwx.dao.CommentDao;
import com.zwx.domain.AdminBlogRequestParam;
import com.zwx.domain.Blog;
import com.zwx.exception.NotFoundException;
import com.zwx.utils.MarkdownUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-06 15:17
 */
@Service
public class BlogServiceImp implements BlogService {

    @Resource
    private BlogDao blogDao;
    @Resource
    private CommentDao commentDao;

    @Override
    public List<Blog> listBlogByDynParam(AdminBlogRequestParam abrp, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogDao.listBlogByDynParam(abrp);
    }

    @Override
    public List<Blog> listBlog(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogDao.listBlog();
    }

    @Override
    public List<Blog> listBlogAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return blogDao.listBlogAll();
    }

    @Override
    public int saveBlog(Blog blog) {
        //插入博客表自己的所有信息
        blogDao.saveBlog(blog);
        //插入博客数据后，查询一下博客id
        Integer blogId = blogDao.getBlogIdByTitle(blog.getTitle());//获取博客id
        //插入博客标签中间表对应的信息，维护多对多关系
        Integer i = blogTag(blogId,blog);
        return i;
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public Integer getBlogIdByTitle(String title) {
        return blogDao.getBlogIdByTitle(title);
    }

    @Override
    public Integer updateBlog(Blog blog) {
        //将系统时间赋值给修改时间
        blog.setUpdateTime(new Date());
        //首先修改博客表所有有关字段的信息
        blogDao.updateBlog(blog);
        //然后修改博客标签中间表多对多的标签关系
        //第一步：删除所有该博客的标签信息
        Integer blogId = blog.getId();
        blogDao.deleteBlogManyToMany(blogId);
        //第二步重新添加新的博客标签的信息
        Integer i = blogTag(blogId, blog);
        return i;
    }

    @Override
    public Integer deleteBlog(Integer blogId) {
        //要删除博客首先需要删除所有评论和标签依赖
        commentDao.deleteCommentsByBlogId(blogId);//删除博客下的所有评论
        blogDao.deleteBlogManyToMany(blogId);//删除中间表博客标签依赖
        //然后才能删除博客
        return blogDao.deleteBlogById(blogId);
    }

    @Override
    public List<Blog> getRecommend4Blog() {
        return blogDao.getRecommend4Blog();
    }

    @Override
    public List<Blog> listBlogQuery(Integer page,Integer size,String query) {
        PageHelper.startPage(page,size);
        return blogDao.listBlogQuery(query);
    }

    @Override
    public Blog getBlogDetailById(Integer id) {
        Blog blog = blogDao.getBlogDetailById(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }else {
            //浏览量加一
            blogDao.viewsAdd1(id);
        }
        //处理将博客内容从Markdown转成HTML文本
        String content = blog.getContent();
        String a = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setContent(a);
        return blog;
    }

    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    @Override
    public Integer getBlogViewsTotal() {
        return blogDao.getBlogViewsTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    @Override
    public List<Blog> listBlogByTypeId(Integer page, Integer size, Integer id) {
        PageHelper.startPage(page,size);
        return blogDao.listBlogByTypeId(id);
    }

    @Override
    public List<Blog> listBlogByTagId(Integer page, Integer size, Integer id) {
        PageHelper.startPage(page,size);
        return blogDao.listBlogByTagId(id);
    }

    //插入博客标签中间表的方法
    private int blogTag(Integer blogId,Blog blog){
        String ids = blog.getTagIds();//获取多个逗号隔开的标签id的字符串
        if (ids != null && ids != "") {//如果有标签信息
            for (String id : ids.split(",")) {//遍历多个标签id字符串，拿到单个标签id，插入数据库
                Integer tagId = Integer.parseInt(id);
                blogDao.saveBlogManyToMany(blogId, tagId);
            }
        }
        return 1;
    }

}
