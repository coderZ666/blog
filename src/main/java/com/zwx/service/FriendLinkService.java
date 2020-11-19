package com.zwx.service;

import com.zwx.domain.FriendLink;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处理友情链接的业务逻辑层
 * @author coderZWX
 * @date 2020-11-11 16:00
 */
@Transactional
public interface FriendLinkService {

    /**
     * 查询所有的友链信息
     * @return 查到的友链集合
     */
    List<FriendLink> listFriendLink(Integer page,Integer size);

    /**
     * 查询友链博客地址是否重复
     * @param blogAddress 博客地址
     * @return 是否重复
     */
    boolean addressIsExist(String blogAddress);

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

    /**
     * 查询所有友链不分页
     * @return 查到的友链集合
     */
    List<FriendLink> listFriendLink();
}
