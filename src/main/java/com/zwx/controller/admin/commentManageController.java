package com.zwx.controller.admin;

import com.zwx.domain.Comment;
import com.zwx.service.CommentService;
import com.zwx.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 留言管理控制器
 * @author coderZWX
 * @date 2020-11-13 10:46
 */
@Controller
@RequestMapping("/admin")
public class commentManageController {

    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;

    //进入留言管理页面，显示所有含有敏感词的评论
    @GetMapping("/comment")
    public String adminMessage(Model model){
        List<Comment> sensitiveComment = commentService.listSensitiveComment();
        model.addAttribute("comments",sensitiveComment);
        return "admin/comment";
    }

    //进入评论修改页面，回显需要修改的内容
    @GetMapping("/comment/{id}/update")
    public String toUpdate(@PathVariable Integer id,Model model){
        model.addAttribute("comment",commentService.getCommentById(id));
        return "admin/comment-input";
    }

    //提交修改后的评论
    @PostMapping("/comment/{id}/update")
    public String update(@PathVariable Integer id, Comment comment, RedirectAttributes attributes){
        comment.setId(id);
        Integer i = commentService.updateSensitiveComment(comment);
        if (i==1){
            attributes.addFlashAttribute("message","修改成功");
        }else {
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/comment";
    }

    //删除评论
    @GetMapping("/comment/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        commentService.deleteComment(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/comment";
    }

    //删除所有敏感评论
    @GetMapping("/deleteAllSensitiveComment")
    public String deleteAll(RedirectAttributes attributes){
        Integer i = commentService.deleteSensitiveComment();
        attributes.addFlashAttribute("message","批量删除成功，共删除评论 "+i+" 条");
        return "redirect:/admin/comment";
    }

    //关闭所有博客评论功能
    @GetMapping("/closeComment")
    public String closeComment(RedirectAttributes attributes){
        commentService.closeComment();
        attributes.addFlashAttribute("message","成功关闭评论功能");
        return "redirect:/admin/comment";
    }

    //开启所有博客评论功能
    @GetMapping("/openComment")
    public String openMessage(RedirectAttributes attributes){
        commentService.openComment();
        attributes.addFlashAttribute("message","成功开启评论功能");
        return "redirect:/admin/comment";
    }

    //进入查看最新评论页面的请求
    @GetMapping("/newComment")
    public String newComment(Model model){
        //查到所有未读评论
        List<Comment> comments = commentService.listUnreadComment();
        //发送给页面
        model.addAttribute("comments",comments);
        return "admin/newComment";
    }

    //标为已读的请求
    @GetMapping("/isRead")
    public String isRead(RedirectAttributes attributes){
        //更新最后一次读评论时间为当前系统时间
        userService.lastReadCommentTime();
        attributes.addFlashAttribute("message","成功标为已读");
        return "redirect:/admin/newComment";
    }

}
