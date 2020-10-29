package com.oldou.dao;

import com.oldou.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类持久层
 */
@Mapper
@Repository
public interface TypeDao {
    /**
     * 新增一个分类
     * @param type
     * @return
     */
    int saveType(Type type);

    /**
     * 根据ID获取一个分类
     * @param id
     * @return 这个id的分类
     */
    Type getTypeById(Long id);

    /**
     * 获取所有分类
     * @return 返回所有分类
     */
    List<Type> getAllType();

    /**
     * 获取所有的分类和博客
     * @return 所有分类和博客
     */
    List<Type> getAllTypeAndBlog();

    /**
     * 根据名字获取分类
     * @param name 分类名
     * @return 分类
     */
    Type getTypeByName(String name);

    /**
     * 修改分类
     * @param type 分类对象
     * @return 分类对象
     */
    int updateType(Type type);

    /**
     * 根据分类ID删除分类
     * @param id 分类id
     */
    void deleteType(Long id);

}
