package com.oldou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 关于我界面显示控制器
 */
@Controller
public class AboutShowController {
    /**
     * 直接跳转到关于我的界面
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }

}