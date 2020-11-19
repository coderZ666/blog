package com.zwx.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwx.domain.FriendLink;
import com.zwx.service.FriendLinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接管理的控制器
 * @author coderZWX
 * @date 2020-11-11 15:38
 */
@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

    //默认每页展示十条数据
    private Integer size=10;

    //访问友链管理页面的请求
    @GetMapping("/friendLinks")
    public String toFriendLinks(Model model,
            //获取请求参数，表示当前是第几页，不能为空，默认为1
            @RequestParam(name="page",defaultValue="1")Integer page){
        List<FriendLink> friendLinks = friendLinkService.listFriendLink(page, size);
        PageInfo pageInfo = new PageInfo(friendLinks);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendLinks";
    }

    //处理访问友链新增页面的请求
    @GetMapping("/friendLinks/input")
    public String input() {
        return "admin/friendLinks-input";
    }

    //处理友链新增的请求
    @PostMapping("/friendLinks/input")
    public String post(FriendLink friendLink, RedirectAttributes attributes,Model model){
        //首先判断博客地址是否已存在
        if (friendLinkService.addressIsExist(friendLink.getBlogAddress())) {//该地址已存在
            model.addAttribute("message", "不能添加相同的网址");
            return "admin/friendLinks-input";
        }
        //将新增信息存入数据库
        int i = friendLinkService.saveFriendLink(friendLink);
        if (i == 0 ) {
            attributes.addFlashAttribute("message", "发生异常，新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/friendLinks";
    }

    //处理访问友链修改页面的请求
    @GetMapping("/friendLinks/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("friendLink", friendLinkService.getFriendLinkById(id));
        return "admin/friendLinks-input";
    }

    //处理修改友链的请求
    @PostMapping("/friendLinks/{id}/input")
    public String editPost(@PathVariable Integer id,FriendLink friendLink, RedirectAttributes attributes,Model model) {
        //首先判断博客地址是否已存在
        if (friendLinkService.addressIsExist(friendLink.getBlogAddress())) {//该地址已存在,返回修改页面，回传一个friendLink
            friendLink.setId(id);
            model.addAttribute("friendLink",friendLink);
            model.addAttribute("message", "不能添加相同的地址");
            return "admin/friendLinks-input";
        }
        //地址不存在则更改新的信息到友链中
        friendLink.setId(id);
        int t = friendLinkService.updateFriendLink(friendLink);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "未知的异常，编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/friendLinks";
    }

    //删除友链
    @GetMapping("/friendLinks/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/friendLinks";
    }

}
