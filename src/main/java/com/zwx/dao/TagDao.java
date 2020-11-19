package com.zwx.dao;

import com.zwx.domain.Tag;
import com.zwx.domain.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作标签表的数据访问层
 * @author coderZWX
 * @date 2020-11-05 18:36
 */
@Mapper
public interface TagDao {

    /**
     * 查询全部标签
     * @return 封装全部tag类型对象的集合
     */
    List<Tag> listTag();

    /**
     * 新增一个标签
     * @param tag 封装新增标签信息的tag对象
     * @return 新增的条数
     */
    int saveTag(Tag tag);

    /**
     * 根据name查询一个标签
     * @param name 标签名称
     * @return 返回标签对象
     */
    Tag getTagByName(String name);

    /**
     * 根据id查询一个标签
     * @param id 标签id
     * @return 返回标签对象
     */
    Tag getTagById(Integer id);

    /**
     * 修改一个标签
     * @param tag 封装修改信息的标签对象
     * @return 修改的条数
     */
    int updateTag(Tag tag);

    /**
     * 删除一个标签
     * @param id 被删除标签的id
     * @return 删除的条数
     */
    int deleteTag(Integer id);

    /**
     * 通过博客id查询所有该博客标注的标签
     * @param id 博客id
     * @return 该博客对应的标签集合
     */
    List<Tag> getTagsByBlogId(Integer id);

    /**
     * 根据标签id查询该标签对应的博客数量
     * @param id 标签id
     * @return 博客数量
     */
    Integer getBlogNum(Integer id);

}