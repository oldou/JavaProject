package com.oldou.service;


import com.oldou.pojo.Message;

import java.util.List;

/**
 * 留言业务层接口
 */
public interface MessageService {

    /**
     * 查询留言列表
     * @return 返回留言列表
     */
    List<Message> listMessage();

    /**
     * 保存留言
     * @param message 留言信息
     * @return 保存是否成功的状态
     */
    int saveMessage(Message message);

    /**
     * 根据ID删除留言
     * @param id
     */
    void deleteMessage(Long id);

}