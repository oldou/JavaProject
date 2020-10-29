package com.oldou.dao;

import com.oldou.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论持久层接口
 */
@Mapper
@Repository
public interface CommentDao {

    /**
     * 查询最顶级的评论
     * @param blogId 博客编号
     * @param blogParentId 评论的编号 -1表示最顶级的评论
     * @return
     */
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);


    /**
     * 查询一级回复
     * @param blogId 博客编号
     * @param id 评论编号
     * @return
     */
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);

    /**
     * 查询二级回复
     * @param blogId
     * @param childId
     * @return
     */
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId, @Param("childId") Long childId);

    //查询父级对象
//    Comment findByParentCommentId(Long parentCommentId);


    /**
     * 添加一个评论
     * @param comment
     * @return
     */
    int saveComment(Comment comment);

    /**
     * 删除评论
     * @param id
     */
    void deleteComment(Long id);

}