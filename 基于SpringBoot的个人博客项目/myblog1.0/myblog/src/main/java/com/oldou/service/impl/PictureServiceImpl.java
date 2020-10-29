package com.oldou.service.impl;

import com.oldou.dao.PictureDao;
import com.oldou.pojo.Picture;
import com.oldou.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 照片墙业务层接口实现类
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }
    @Transactional
    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }
    @Transactional
    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }
    @Transactional
    @Override
    public void deletePicture(Long id) {
        pictureDao.deletePicture(id);
    }

}