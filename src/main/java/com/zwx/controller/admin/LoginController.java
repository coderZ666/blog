package com.zwx.controller.admin;

import com.zwx.domain.UserInfo;
import com.zwx.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author coderZWX
 * @date 2020-11-05 15:29
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Resource
    private UserService userService;

    //访问登录页的请求
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    //处理登录表单验证的请求
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, RedirectAttributes ra){
        UserInfo userInfo = userService.checkUser(username, password);
        if (userInfo != null){//如果查到了，表示登录成功
            //将密码设置为空
            userInfo.setPassword(null);
            //将登录信息存放在session中
            session.setAttribute("user",userInfo);
            //请求转发到首页面
            return "admin/index";
        }else {//没查到表示登录失败，重定向到登录页面，传递一个错误信息
            ra.addFlashAttribute("massage","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    //处理注销登录的请求
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //清除session中的登录信息
        session.removeAttribute("user");
        //重定向到登录页面
        return "redirect:/admin";
    }

}
