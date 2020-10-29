package com.oldou.dao;

import com.oldou.pojo.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 照片墙持久层接口
 */
@Mapper
@Repository
public interface PictureDao {
    /**
     * 获取所有的照片
     * @return 返回照片集合
     */
    List<Picture> listPicture();

    /**
     * 保存照片
     * @param picture 照片的对象
     * @return 返回状态
     */
    int savePicture(Picture picture);

    /**
     * 根据ID获取照片
     * @param id 照片编号
     * @return 照片对象
     */
    Picture getPicture(Long id);

    /**
     *更新照片信息
     * @param picture 照片对象
     * @return 更新状态
     */
    int updatePicture(Picture picture);

    /**
     * 根据ID删除照片
     * @param id
     */
    void deletePicture(Long id);

}