package com.zwx.controller;

import com.zwx.domain.FriendLink;
import com.zwx.service.FriendLinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-11 17:54
 */
@Controller
public class FriendLinkShowController {

    @Resource
    private FriendLinkService friendLinkService;

    //访问友人帐页的请求
    @GetMapping("/friendLink")
    public String friendLink(Model model){
        List<FriendLink> friendLinks = friendLinkService.listFriendLink();
        model.addAttribute("friendLinks",friendLinks);
        return "friends";
    }

}
