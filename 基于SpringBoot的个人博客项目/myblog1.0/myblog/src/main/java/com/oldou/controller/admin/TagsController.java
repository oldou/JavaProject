package com.oldou.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oldou.pojo.Tag;
import com.oldou.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagsController {
    @Autowired
    private TagService tagService;

    //分页查询列表
    @GetMapping("/tags")
    public String getAllTag(Model model,
             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //按照排序字段升序排序
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(allTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /**
     * 跳转到增加标签页面
     * @param model 传值
     * @return 转到增加标签页面
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String postTag(Tag tag, RedirectAttributes attributes){
        //先判断数据库中是否有这个元素，有就提示
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null){
            attributes.addFlashAttribute("message","不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }

        //没有这个元素就添加
        int i = tagService.saveTag(tag);
        if(i>0){
            attributes.addAttribute("message","操作成功！");
        }else{
            attributes.addAttribute("message","操作失败！");
        }
        return "redirect:/admin/tags";
    }

    //跳转到修改分类页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    //编辑修改分类
    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, RedirectAttributes attributes, @PathVariable("id") String id) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }
        int t = tagService.updateTag(tag);
        if (t == 0 ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }



}
