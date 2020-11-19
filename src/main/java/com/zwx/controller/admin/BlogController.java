package com.zwx.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.AdminBlogRequestParam;
import com.zwx.domain.Blog;
import com.zwx.domain.UserInfo;
import com.zwx.service.BlogService;
import com.zwx.service.TagService;
import com.zwx.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-06 13:24
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Resource
    private BlogService blogService;
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;
    //默认每页展示十条数据
    private Integer size=10;

    //处理访问博客管理页面的请求
    @GetMapping()
    public String blogs(Model model,
                        //获取请求参数，表示当前是第几页，不能为空，默认为1
                        @RequestParam(name="page",defaultValue="1")Integer page){
        model.addAttribute("types",typeService.listType());
        List<Blog> blogs = blogService.listBlog(page, size);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    //点击搜索和上下一页时访问博客管理页面的请求
    @PostMapping("/search")
    public String search(Model model, AdminBlogRequestParam abrp,
                        //获取请求参数，表示当前是第几页，不能为空，默认为1
                        @RequestParam(name="page",defaultValue="1")Integer page){
        List<Blog> blogs = blogService.listBlogByDynParam(abrp, page, size);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs :: blogList";
    }

    //处理进入新增博客页面的请求
    @GetMapping("/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        return "admin/blogs-input";
    }

    //处理新增博客的请求
    @PostMapping("/input")
    public String postInput(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUserInfo((UserInfo) session.getAttribute("user"));
        int i = blogService.saveBlog(blog);
        if (i == 1){//新发布博客成功
            attributes.addFlashAttribute("massage","发布成功");
        }else {//发布失败
            attributes.addFlashAttribute("massage","未知的异常，发布失败");
        }
        return "redirect:/admin/blogs";
    }

    //处理进入修改页面的请求
    @GetMapping("/{id}/input")
    public String editInput(@PathVariable Integer id, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",blogService.getBlogById(id));
        return "admin/blogs-input";
    }

    //处理修改博客的请求
    @PostMapping("/input/{id}")
    public String postEditInput(@PathVariable Integer id, Blog blog, RedirectAttributes attributes){
        blog.setId(id);
        int i = blogService.updateBlog(blog);
        if (i == 1){//修改博客成功
            attributes.addFlashAttribute("massage","修改成功");
        }else {//修改失败
            attributes.addFlashAttribute("massage","未知的异常，修改失败");
        }
        return "redirect:/admin/blogs";
    }

    //处理删除博客的请求
    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Integer id,RedirectAttributes attributes){
        Integer i = blogService.deleteBlog(id);
        if (i == 1){
            attributes.addFlashAttribute("massage","删除成功");
        }else {
            attributes.addFlashAttribute("massage","未知的异常，删除失败");
        }
        return "redirect:/admin/blogs";
    }

}
