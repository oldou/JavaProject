package com.oldou.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oldou.dao.BlogDao;
import com.oldou.pojo.Comment;
import com.oldou.queryvo.DetailedBlog;
import com.oldou.queryvo.FirstPageBlog;
import com.oldou.queryvo.RecommendBlog;
import com.oldou.service.BlogService;
import com.oldou.service.CommentService;
import com.oldou.service.TagService;
import com.oldou.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 博客首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    /**
     * 跳转到首页，同时分页显示首页的所有博客以及推荐博客
     * @param pageNum 当前页
     * @return 跳转到首页
     */
    @GetMapping({"/","/index","index.html"})
    public String index(Model model, RedirectAttributes attributes,
           @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        //每页显示10篇博客
        PageHelper.startPage(pageNum,10);
        //查询所有的博客
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        //查询推荐的博客
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        //将所有的的博客进行分页处理
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendedBlog);

        return "index";
    }

    /**
     * 搜索博客
     * @param pageNum 当前页
     * @param query 搜索的内容
     * @return 搜索结果页面
     */
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        //pagesize：每页的数量
        PageHelper.startPage(pageNum, 1000);
        //根据输入的查询内容搜索博客
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        //将查询的博客放入到分页进行处理
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        //将自己要查询的内容回显到博客搜索处
        model.addAttribute("query", query);
        return "search";
    }

    //跳转博客详情页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        //根据ID获取博客的详情信息，同时业务层将HTML格式的正文-->MarkDown格式
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        //根据ID获取所有的评论信息
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

//    最新博客列表
//    @GetMapping("/footer/newblog")
//    public String newblogs(Model model) {
//        List<FirstPageBlog> newBlog = blogService.getNewBlog();
//        model.addAttribute("newblogs", newBlog);
//        return "index :: newblogList";
//    }

    //底部栏的博客信息统计
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        //获取博客总数
        int blogTotal = blogService.getBlogTotal();
        //获取访问总数
        int blogViewTotal = blogService.getBlogViewTotal();
        //获取评论总数
        int blogCommentTotal = blogService.getBlogCommentTotal();
        //获取留言总数
        int blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }

}
