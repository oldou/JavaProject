package com.oldou.service;

import com.oldou.pojo.FriendLink;

import java.util.List;

/**
 * 友链业务层接口
 */
public interface FriendLinkService {

    /**
     * 获取所有的友链
     * @return 返回一个友链集合
     */
    List<FriendLink> listFriendLink();

    /**
     * 保存友链信息
     * @param friendLink
     * @return 返回状态
     */
    int saveFriendLink(FriendLink friendLink);
    /**
     * 根据ID获取友链信息
     * @param id 友链编号
     * @return
     */
    FriendLink getFriendLink(Long id);

    /**
     * 根据博客地址获取友链
     * @param blogaddress 博客地址
     * @return
     */
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    /**
     * 更新友链信息
     * @param friendLink 友链对象
     * @return 返回更新状态
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 根据ID删除友链
     * @param id 友链编号
     */
    void deleteFriendLink(Long id);


}