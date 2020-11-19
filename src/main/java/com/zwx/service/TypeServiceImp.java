package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.TypeDao;
import com.zwx.domain.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-05 18:35
 */
@Service
public class TypeServiceImp implements TypeService {

    @Resource
    private TypeDao typeDao;

    @Override
    public List<Type> listType(int page,int size) {
        PageHelper.startPage(page,size);
        return typeDao.listType();
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeDao.deleteType(id);
    }

    @Override
    public Integer getBlogNum(Integer id) {
        return typeDao.getBlogNum(id);
    }

}
