package com.oldou.service;

import com.oldou.pojo.Tag;

import java.util.List;

/**
 * 文章标签业务层
 */
public interface TagService {

    /**
     * 新增一个标签
     * @param tag
     * @return
     */
    int saveTag(Tag tag);

    /**
     * 根据ID获取一个标签
     * @param id
     * @return 这个id的标签
     */
    Tag getTagById(Long id);

    /**
     * 获取所有标签
     * @return 返回所有标签
     */
    List<Tag> getAllTag();

    /**
     * 获取所有的标签和博客
     * @return 所有标签和博客
     */
    List<Tag> getAllTagAndBlog();

    /**
     * 查询一定范围内的id对应的Tag
     * @param ids
     * @return
     */
    List<Tag> ListTag(String ids);


    /**
     * 根据名字获取标签
     * @param name 标签名
     * @return 标签
     */
    Tag getTagByName(String name);

    /**
     * 修改标签
     * @param tag 标签对象
     * @return 标签对象
     */
    int updateTag(Tag tag);

    /**
     * 根据标签 ID 删除标签
     * @param id 标签id
     */
    void deleteTag(Long id);

}
