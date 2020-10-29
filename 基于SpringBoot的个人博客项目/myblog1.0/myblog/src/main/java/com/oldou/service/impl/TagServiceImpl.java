package com.oldou.service.impl;

import com.oldou.dao.TagDao;
import com.oldou.pojo.Tag;
import com.oldou.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getAllTagAndBlog() {
        return tagDao.getAllTagAndBlog();
    }

    @Override
    public List<Tag> ListTag(String ids) {
        List<Long> idList = convertToList(ids);
        for (Long aLong : idList) {
            System.out.println("*********************:"+aLong+"\t");
        }
        return tagDao.getListIdTag(idList);
    }
    //将id字符串转出List集合
    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] idarray = ids.split(",");//通过,进行分割
            //List<String> list1 = Arrays.asList(idarray);
            for(int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }
}
