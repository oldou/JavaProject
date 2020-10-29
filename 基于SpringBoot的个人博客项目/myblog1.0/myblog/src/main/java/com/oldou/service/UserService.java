package com.oldou.service;

import com.oldou.pojo.User;

/**
 * 用户业务层接口
 */
public interface UserService {
    /**
     * 校验用户账号和密码
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User checkUser(String username,String password);
}
