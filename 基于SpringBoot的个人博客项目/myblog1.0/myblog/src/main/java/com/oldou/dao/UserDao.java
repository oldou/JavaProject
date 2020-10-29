package com.oldou.dao;

import com.oldou.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层接口
 */
@Mapper
@Repository
public interface UserDao {
    /**
     *
     * @param username 登录用户名
     * @param password 登录密码
     * @return 返回查询的用户对象
     */
    User findUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
