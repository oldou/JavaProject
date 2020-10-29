package com.oldou.controller.admin;

import com.oldou.pojo.User;
import com.oldou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 用户登录控制器：登录后台管理界面
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @return 跳转到登录页面
     */
    @GetMapping    //使用全局，访问admin就能跳转到该页面
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录的校验处理
     * @param username 用户名
     * @param password 密码
     * @param attributes 用于传递给前端的信息的对象
     * @param session 用于记住用户信息
     * @return 登录成功返回管理首页，登录失败返回失败信息并返回登录页面
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        RedirectAttributes attributes,
                        HttpSession session){
        User user = userService.checkUser(username, password);
        if(user != null){
            user.setPassword(null);//登录成功以后将密码设置为null，防止密码传到页面，增加安全性
            session.setAttribute("user",user);
            return "admin/index"; //登录成功就返回到首页
        } else {
            //这里由于是重定向,所以不能用Model对象来传值
            attributes.addFlashAttribute("message","用户名或密码不正确！");
            return "redirect:/admin"; //登录失败就返回到登录页面
        }
    }

    /**
     * 注销：销毁session中所存储的用户信息
     * @param session 用于删除用户信息
     * @return 重定向到登录页面
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
