package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.TagDao;
import com.zwx.domain.Tag;
import com.zwx.domain.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-05 18:35
 */
@Service
public class TagServiceImp implements TagService {

    @Resource
    private TagDao tagDao;

    @Override
    public List<Tag> listTag(int page, int size) {
        PageHelper.startPage(page,size);
        return tagDao.listTag();
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagDao.getTagById(id);
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
    public int deleteTag(Integer id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public Integer getBlogNum(Integer id) {
        return tagDao.getBlogNum(id);
    }

}