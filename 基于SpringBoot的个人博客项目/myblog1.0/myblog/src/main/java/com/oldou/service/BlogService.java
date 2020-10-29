package com.oldou.service;

import com.oldou.pojo.Blog;
import com.oldou.queryvo.*;

import java.util.List;

/**
 * 博客业务层接口
 */
public interface BlogService {
    /**
     * 根据id查询博客
     * @param id 博客id
     * @return 返回查询的博客对象
     */
    ShowBlog getBlogById(Long id);

    /**
     * 获取所有的博客
     * @return 返回博客的集合
     */
    List<BlogQuery> getAllBlog();

    /**
     * 获取文章管理列表
     * @return
     */
/*    List<BlogQuery> getAllQueryBlog();*/

    /**
     * 增加博客
     * @param blog 博客对象
     * @return 返回博客对象
     */
    int saveBlog(Blog blog);

    /**
     * 根据id删除博客信息
     * @param id 博客id
     */
    void deleteBlog(Long id);

    /**
     * 更新博客信息
     * @param showBlog 博客对象
     * @return 状态
     */
    int updateBlog(ShowBlog showBlog);

    /**
     * 根据标题或者分类或者推荐查询博客
     * @param searchBlog
     * @return
     */
    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    /**
     * 获取所有首页的博客
     * @return
     */
    List<FirstPageBlog> getAllFirstPageBlog();

    /**
     * 获取推荐的博客
     * @return
     */
    List<RecommendBlog> getRecommendedBlog();

    //    List<FirstPageBlog> getNewBlog();

    /**
     * 获取搜寻的博客
     * @param query
     * @return
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 通过id获取详细的博客
     * @param id
     * @return
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 根据TypeId获取博客列表，在分类页进行的操作
     */
    List<FirstPageBlog> getByTypeId(Long typeId);

    /**
     * 获取博客总数
     * @return
     */
    Integer getBlogTotal();

    /**
     * 获取访问总数
     * @return
     */
    Integer getBlogViewTotal();

    /**
     * 获取评论总数
     * @return
     */
    Integer getBlogCommentTotal();

    /**
     * 获取留言总数
     * @return
     */
    Integer getBlogMessageTotal();


}
