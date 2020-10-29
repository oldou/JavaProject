package com.oldou.dao;

import com.oldou.pojo.Blog;
import com.oldou.queryvo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客数据层接口
 */
@Mapper
@Repository
public interface BlogDao {
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
    List<BlogQuery> getAllBlogQuery();


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
     * 根据标题或者分类或者标签或者推荐查询博客
     * @param searchBlog
     * @return
     */
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    /**
     * 获取所有首页的博客
     * @return
     */
    List<FirstPageBlog> getFirstPageBlog();

    /**
     * 获取推荐的博客
     * @return
     */
    List<RecommendBlog> getAllRecommendBlog();

    /**
     * 获取首页所有的博客
     * @param query
     * @return
     */
    List<FirstPageBlog> getSearchBlog(String query);

    /**
     * 根据ID获取该博客
     * @param id
     * @return
     */
    DetailedBlog getDetailedBlog(Long id);

    /**
     * 更新访问
     * @param id
     * @return
     */
    int updateViews(Long id);

    /**
     * 根据博客id查询出评论数量
     */
    int getCommentCountById(Long id);

    /**
     * 通过类型查找博客
     * @param typeId
     * @return
     */
    List<FirstPageBlog> getByTypeId(Long typeId);

    /**
     * 获取博客总数
     * @return
     */
    Integer getBlogTotal();

    /**
     * 获取博客访问量总数
     * @return
     */
    Integer getBlogViewTotal();

    /**
     * 获取博客总评论数
     * @return
     */
    Integer getBlogCommentTotal();

    /**
     * 获取博客总留言数
     * @return
     */
    Integer getBlogMessageTotal();


}
