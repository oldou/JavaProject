package com.oldou.service;


import com.oldou.pojo.Comment;

import java.util.List;

/**
 * 博客评论业务层接口
 */
public interface CommentService {
    /**
     * 根据创建时间将评论倒序排序
     * @param blogId
     * @return
     */
    List<Comment> listCommentByBlogId(Long blogId);

    /**
     * 保存评论
     * @param comment 评论信息
     * @return 保存状态
     */
    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    /**
     * 删除评论
     */
    void deleteComment(Comment comment, Long id);

}