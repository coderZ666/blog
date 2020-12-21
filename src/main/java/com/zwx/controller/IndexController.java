package com.zwx.controller;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Blog;
import com.zwx.domain.Comment;
import com.zwx.service.BlogService;
import com.zwx.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * 处理有关首页请求的控制器
 * @author coderZWX
 * @date 2020-11-07 19:47
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;
    @Resource
    private CommentService commentService;

    //初始化赋值每页展示博客的条数为10
    private Integer size=10;

    //访问根路径就可以直接来到首页
    @GetMapping("/")
    public String index(Model model,
                        //获取请求参数，表示当前是第几页，不能为空，默认为1
                        @RequestParam(name="page",defaultValue="1")Integer page){
        //获取四个最新推荐（仅需要首图地址和标题和id）传入页面
        List<Blog> recommend4Blog = blogService.getRecommend4Blog();
        model.addAttribute("recommends",recommend4Blog);
        //获取博客列表传入页面
        List<Blog> blogs = blogService.listBlogAll(page, size);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }

    //处理查询的请求，仅针对标题和内容的模糊查询
    @PostMapping("/search")
    public String search(Model model,String query,
                         //获取请求参数，表示当前是第几页，不能为空，默认为1
                         @RequestParam(name="page",defaultValue="1")Integer page){
        //获取搜索结果的博客列表传入页面
        List<Blog> blogs = blogService.listBlogQuery(page,size,query);
        model.addAttribute("query",query);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }
    //处理查询上下一页的请求，仅针对标题和内容的模糊查询
    @GetMapping("/search")
    public String searchPage(Model model,String query,
                         //获取请求参数，表示当前是第几页，不能为空，默认为1
                         @RequestParam(name="page",defaultValue="1")Integer page){
        //获取搜索结果的博客列表传入页面
        List<Blog> blogs = blogService.listBlogQuery(page,size,query);
        model.addAttribute("query",query);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }

    //进入博客详情页面的请求
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id,Model model){
        //将博客传入页面
        Blog blog = blogService.getBlogDetailById(id);
        //博客评论传入页面(分页)
        List<Comment> comments = commentService.getCommentsByBlogId(id,1,size);
        PageInfo pageInfo = new PageInfo(comments);
        //博客置顶评论传入页面
        List<Comment> topComments = commentService.getTopCommentsByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("topComments",topComments);
        return "blog";
        //浏览量加1
    }


    //查询博客信息（博客总数、博客浏览量总数、博客评论总数、留言总数）
    @GetMapping("/footer")
    public String blogMessage(Model model){
        Integer blogTotal = blogService.getBlogTotal();
        Integer blogViewTotal = blogService.getBlogViewsTotal();
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
//        Integer blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
//        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "_fragments :: message";
    }

    //处理最新文章列表上下一页的请求
    @PostMapping("/changeBlogPage")
    public String changeBlogPage(Integer page,Model model){
        System.out.println("进入方法"+page);
        //获取博客列表传入页面
        List<Blog> blogs = blogService.listBlogAll(page, size);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "index :: blogList";
    }

    //访问音乐盒页的请求
    @GetMapping("/music")
    public String music(){
        return "music";
    }
    //访问关于我页的请求
    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
