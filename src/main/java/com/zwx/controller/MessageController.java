package com.zwx.controller;

import com.github.pagehelper.PageInfo;
import com.zwx.domain.Blog;
import com.zwx.domain.Comment;
import com.zwx.domain.Message;
import com.zwx.domain.UserInfo;
import com.zwx.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理关于留言版请求的控制器
 * @author coderZWX
 * @date 2020-11-10 21:14
 */
@Controller
public class MessageController {

    private Integer size=10;

    @Resource
    private MessageService messageService;

    //处理访问留言板的请求
    @GetMapping("/toMessage")
    public String toMessage(Model model,
                            //获取请求参数，表示当前是第几页，不能为空，默认为1
                            @RequestParam(name="page",defaultValue="1")Integer page){
        //查询留言是否开启传递给页面
        boolean b = messageService.messageOpen();
        model.addAttribute("openOrNot",b);
        //分页查询留言信息传递给页面
        List<Message> messages = messageService.listMessage(page, size);
        PageInfo pageInfo = new PageInfo(messages);
        model.addAttribute("pageInfo",pageInfo);
        return "message";
    }

    //处理刷新留言板的请求
    @GetMapping("/message")
    public String message(Model model,
                            //获取请求参数，表示当前是第几页，不能为空，默认为1
                            @RequestParam(name="page",defaultValue="1")Integer page){
        //查询留言是否开启传递给页面
        boolean b = messageService.messageOpen();
        model.addAttribute("openOrNot",b);
        //分页查询留言信息传递给页面
        List<Message> messages = messageService.listMessage(page, size);
        PageInfo pageInfo = new PageInfo(messages);
        model.addAttribute("pageInfo",pageInfo);
        return "message :: commentList";
    }

    //改变留言当前页的请求
    @PostMapping("/changeMessagePage")
    public String changeCommentPage(Integer page){
        return "redirect:/message" + "?page=" + page;
    }

    //发布留言的请求
    @PostMapping("/message/{page}")
    public String saveMessage(@PathVariable Integer page, Message message, HttpSession session){
        if (message.getHeadUrl()==null){//如果后台没有传来头像信息，说明是管理员留言，拿到session中博主头像地址赋值
            UserInfo user = (UserInfo) session.getAttribute("user");
            message.setHeadUrl(user.getHeadUrl());
        }
        if (message.getParentId()==-1){//说明是父留言，会展示在最新的一条，让page=1
            page=1;
        }
        //首先保存发布的信息到数据库
        messageService.saveMessage(message);
        //然后刷新留言板
        return "redirect:/message?page="+page;
    }

    //删除留言的请求
    @GetMapping("/message/{id}/delete")
    public String delete(@PathVariable Integer id, Integer page, HttpSession session){
        if (session.getAttribute("user")==null){
            return "error/404";
        }
        messageService.deleteMessage(id);//父子留言都删除
        return "redirect:/toMessage?page="+page;
    }

}
