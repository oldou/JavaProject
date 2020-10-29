package com.oldou.service.impl;

import com.oldou.dao.BlogDao;
import com.oldou.dao.CommentDao;
import com.oldou.pojo.Comment;
import com.oldou.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客评论业务层接口实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * 根据博客ID查询评论列表
     * @param blogId
     * @return
     */
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出顶级的父评论列表
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(Comment comment : comments){ //迭代父评论
            Long id = comment.getId(); //获取父评论ID
            String parentNickname1 = comment.getNickname(); //获取父评论昵称
            //根据父评论ID以及博客ID查询父评论下的所有子评论(一级评论)
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId,id);
            //将查询出的子评论(一级评论)
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     *
     * @param blogId 博客编号
     * @param childComments  root根节点  blog不为空的对象集合
     * @param parentNickname1 父评论的昵称
     */
    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        // 判断是否有一级子评论
        if(childComments.size() > 0){
            //循环迭代所有一级子评论
            for(Comment childComment : childComments){
                //获取一级子评论昵称
                String parentNickname = childComment.getNickname();
                //将一级子评论的昵称设置为父评论昵称
                childComment.setParentNickname(parentNickname1);
                //将评论信息添加到临时集合中
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //递归迭代
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    /**
     * 递归迭代 剥洋葱
     * @param blogId 博客编号
     * @param childId 子评论编号
     * @param parentNickname1 父评论昵称
     */
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子评论回复
        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId,childId);
        //判断是否有评论回复
        if(replayComments.size() > 0){
            //有回复就对回复进行迭代
            for(Comment replayComment : replayComments){
                //获取评论的昵称
                String parentNickname = replayComment.getNickname();
                //将评论的昵称设置为父评论
                replayComment.setParentNickname(parentNickname1);
                //获取评论回复的编号
                Long replayId = replayComment.getId();
                //将所有评论回复信息都添加到临时集合中
                tempReplys.add(replayComment);
                //递归调用
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

    //保存评论
    @Override
    public int saveComment(Comment comment) {
        //设置评论时间
        comment.setCreateTime(new Date());
        //保存评论信息
        int comments = commentDao.saveComment(comment);
        //通过博客编号获取评论次数
        blogDao.getCommentCountById(comment.getBlogId());
        return comments;
    }

    //删除评论
    @Override
    public void deleteComment(Comment comment,Long id) {
        commentDao.deleteComment(id);
        blogDao.getCommentCountById(comment.getBlogId());
    }
}