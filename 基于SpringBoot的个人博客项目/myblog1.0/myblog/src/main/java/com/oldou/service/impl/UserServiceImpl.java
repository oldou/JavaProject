package com.oldou.service.impl;

import com.oldou.dao.UserDao;
import com.oldou.pojo.User;
import com.oldou.service.UserService;
import com.oldou.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        //通过MD5将传过来的密码进行加密操作，然后再根据加盐后的密码进行查询
        return userDao.findUsernameAndPassword(username, MD5Utils.code(password));
    }
}
