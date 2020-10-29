package com.oldou.service.impl;

import com.oldou.dao.FriendLinkDao;
import com.oldou.pojo.FriendLink;
import com.oldou.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 友链业务层接口实现类
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {
    @Autowired
    private FriendLinkDao friendLinkDao;

    @Override
    public List<FriendLink> listFriendLink() {
        return this.friendLinkDao.listFriendLink();
    }
    @Transactional
    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return this.friendLinkDao.saveFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLink(Long id) {
        return this.friendLinkDao.getFriendLink(id);
    }

    @Override
    public FriendLink getFriendLinkByBlogaddress(String blogaddress) {
        return this.friendLinkDao.getFriendLinkByBlogaddress(blogaddress);
    }
    @Transactional
    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return this.friendLinkDao.updateFriendLink(friendLink);
    }
    @Transactional
    @Override
    public void deleteFriendLink(Long id) {
        this.friendLinkDao.deleteFriendLink(id);
    }
}