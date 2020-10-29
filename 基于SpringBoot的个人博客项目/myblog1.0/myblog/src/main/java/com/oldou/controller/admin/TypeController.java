package com.oldou.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oldou.pojo.Type;
import com.oldou.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //分页查询列表
    @GetMapping("/types")
    public String getAllType(Model model,
             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段倒序排序
        String orderBy = "id ASC";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Type> allType = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(allType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String postType(Type type, RedirectAttributes attributes){
        //先判断数据库中是否有这个元素，有就提示
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null){
            attributes.addFlashAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }

        //没有这个元素就添加
        int i = typeService.saveType(type);
        if(i>0){
            attributes.addAttribute("message","操作成功！");
        }else{
            attributes.addAttribute("message","操作失败！");
        }
        return "redirect:/admin/types";
    }

    //跳转到修改分类页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    //编辑修改分类
    @PostMapping("/types/{id}")
    public String editPost(Type type, RedirectAttributes attributes, @PathVariable("id") String id) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t = typeService.updateType(type);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }



}
