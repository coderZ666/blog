package com.zwx.controller.admin;

import com.zwx.domain.Message;
import com.zwx.service.MessageService;
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
public class messageManageController {

    @Resource
    private MessageService messageService;

    //进入留言管理页面，显示所有含有敏感词的留言
    @GetMapping("/message")
    public String adminMessage(Model model){
        List<Message> sensitiveMessage = messageService.listSensitiveMessage();
        model.addAttribute("sMessages",sensitiveMessage);
        return "admin/message";
    }

    //进入留言修改页面，回显需要修改的内容
    @GetMapping("/message/{id}/update")
    public String toUpdate(@PathVariable Integer id,Model model){
        model.addAttribute("message",messageService.getMessageById(id));
        return "admin/message-input";
    }

    //提交修改后的留言
    @PostMapping("/message/{id}/update")
    public String update(@PathVariable Integer id, Message message, RedirectAttributes attributes){
        message.setId(id);
        Integer i = messageService.updateSensitiveMessage(message);
        if (i==1){
            attributes.addFlashAttribute("message","修改成功");
        }else {
            attributes.addFlashAttribute("message","修改失败");
        }
        return "redirect:/admin/message";
    }

    //删除留言
    @GetMapping("/message/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        messageService.deleteMessage(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/message";
    }

    //删除所有敏感留言
    @GetMapping("/deleteAllSensitiveMessage")
    public String deleteAll(RedirectAttributes attributes){
        Integer i = messageService.deleteSensitiveMessage();
        attributes.addFlashAttribute("message","批量删除成功，共删除留言 "+i+" 条");
        return "redirect:/admin/message";
    }

    //关闭留言
    @GetMapping("/closeMessage")
    public String closeMessage(RedirectAttributes attributes){
        messageService.closeMessage();
        attributes.addFlashAttribute("message","成功关闭留言功能");
        return "redirect:/admin/message";
    }

    //开启留言
    @GetMapping("/openMessage")
    public String openMessage(RedirectAttributes attributes){
        messageService.openMessage();
        attributes.addFlashAttribute("message","成功开启留言功能");
        return "redirect:/admin/message";
    }

}
