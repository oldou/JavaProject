package com.oldou.dao;

import com.oldou.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 留言持久层接口
 */
@Mapper
@Repository
public interface MessageDao {
    /**
     * 添加一个评论
     * @param message 评论信息
     * @return 添加状态
     */
    int saveMessage(Message message);

    /**
     * 查询父级评论
     * @param ParentId
     * @return 查询评论的集合
     */
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    /**
     * 查询一级回复
     * @param id 编号
     * @return 评论集合
     */
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    /**
     * 查询二级以及所有子集回复
     * @param childId 子评论编号
     * @return 评论集合
     */
    List<Message> findByReplayId(@Param("childId") Long childId);

    /**
     * 根据编号删除评论信息
     * @param id 评论编号
     */
    void deleteMessage(Long id);

}