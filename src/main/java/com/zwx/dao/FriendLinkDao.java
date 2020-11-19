package com.zwx.dao;

import com.zwx.domain.FriendLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作友链表的访问层
 * @author coderZWX
 * @date 2020-11-11 16:10
 */
@Mapper
public interface FriendLinkDao {

    /**
     * 查询所有的友链信息
     * @return 查到的友链集合
     */
    List<FriendLink> listFriendLink();

    /**
     * 根据博客地址，查询有几条数据
     * @param blogAddress 博客地址
     * @return 数据条数
     */
    Integer getFriendLinkCountByAddress(String blogAddress);

    /**
     * 新增一个友链信息到数据库
     * @param friendLink 封装友链信息的对象
     * @return 新增成功的条数
     */
    int saveFriendLink(FriendLink friendLink);

    /**
     * 根据id查询一条友链信息
     * @param id 友链id
     * @return 查到的友链对象
     */
    FriendLink getFriendLinkById(Integer id);

    /**
     * 修改一条友链信息
     * @param friendLink 封装修改后的友链信息对象
     * @return 修改成功的条数
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 根据友链id删除一个友链
     * @param id 友链id
     */
    void deleteFriendLink(Integer id);
}
