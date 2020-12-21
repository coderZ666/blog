package com.zwx.controller;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Blog;
import com.zwx.domain.Comment;
import com.zwx.domain.UserInfo;
import com.zwx.service.BlogService;
import com.zwx.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 评论功能的控制器
 * @author coderZWX
 * @date 2020-11-08 17:09
 */
@Controller
public class CommentController {

    @Resource
    private CommentService commentService;
    @Resource
    private BlogService blogService;

    private Integer size=10;

    //刷新评论的请求
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Integer page, Model model){
        //传递博客信息
        model.addAttribute("blog",blogService.getBlogDetailById(blogId));
        //传递评论信息
        List<Comment> comments = commentService.getCommentsByBlogId(blogId, page, size);
        PageInfo pageInfo = new PageInfo(comments);
        model.addAttribute("pageInfo",pageInfo);
        //传递置顶评论信息
        List<Comment> topComments = commentService.getTopCommentsByBlogId(blogId);
        model.addAttribute("topComments",topComments);
        return "blog :: commentList";
    }

    //提交评论的请求
    @PostMapping("/comments")
    public String saveComment(Comment comment, Integer page, HttpSession session){
        if (comment.getHeadUrl()==null){//如果后台没有传来头像信息，说明是管理员评论，拿到session中博主头像地址赋值
            UserInfo user = (UserInfo) session.getAttribute("user");
            comment.setHeadUrl(user.getHeadUrl());
        }
        //首先保存提交评论的信息到数据库
        int i = commentService.saveComment(comment);
        //然后请求刷新评论，重新渲染评论片段的页面
        return "redirect:/comments/" + comment.getBlog().getId()+"?page="+page;
    }

    //改变评论当前页的请求
    @PostMapping("/changeCommentPage")
    public String changeCommentPage(Integer blogId, Integer page){
        return "redirect:/comments/" + blogId + "?page=" + page;
    }

    //删除评论的请求
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Integer blogId, @PathVariable Integer id, Model model, HttpSession session){
        if (session.getAttribute("user")==null){
            return "error/404";
        }
        commentService.deleteComment(id);//父子评论都删除
        return "redirect:/blog/"+blogId;
    }

    //置顶评论的请求
    @GetMapping("/comment/{blogId}/{id}/toTop")
    public String toTop(@PathVariable Integer blogId, @PathVariable Integer id, Model model, HttpSession session){
        if (session.getAttribute("user")==null){
            return "error/404";
        }
        commentService.toTopComment(id);//将评论设置为置顶
        return "redirect:/blog/"+blogId;
    }

    //取消置顶评论的请求
    @GetMapping("/comment/{blogId}/{id}/cancelTop")
    public String cancelTop(@PathVariable Integer blogId, @PathVariable Integer id, Model model, HttpSession session){
        if (session.getAttribute("user")==null){
            return "error/404";
        }
        commentService.cancelTopComment(id);//将评论取消置顶
        return "redirect:/blog/"+blogId;
    }

}
