package com.zwx.dao;

import com.zwx.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作类型表的数据访问层
 * @author coderZWX
 * @date 2020-11-05 18:36
 */
@Mapper
public interface TypeDao {

    /**
     * 查询全部类型
     * @return 封装全部type类型对象的集合
     */
    List<Type> listType();

    /**
     * 新增一个类型
     * @param type 封装新增类型信息的type对象
     * @return 新增的条数
     */
    int saveType(Type type);

    /**
     * 根据name查询一个类型
     * @param name 类型名称
     * @return 返回类型对象
     */
    Type getTypeByName(String name);

    /**
     * 根据id查询一个类型
     * @param id 类型id
     * @return 返回类型对象
     */
    Type getTypeById(Integer id);

    /**
     * 修改一个类型
     * @param type 封装修改信息的类型对象
     * @return 修改的条数
     */
    int updateType(Type type);

    /**
     * 删除一个类型
     * @param id 被删除类型的id
     * @return 删除的条数
     */
    int deleteType(Integer id);

    /**
     * 根据分类id查询该分类下博客的数量
     * @param id 分类id
     * @return 博客数量
     */
    Integer getBlogNum(Integer id);

}
