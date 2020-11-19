package com.zwx.dao;

import com.zwx.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 处理用户表的数据访问
 * @author coderZWX
 * @date 2020-11-05 14:12
 */
@Mapper
public interface UserDao {

    /**
     * 通过用户名密码，查询一个用户的基础信息（不含博客集合）
     * @param username 用户名
     * @param password 密码
     * @return 查到的用户
     */
    UserInfo getUserByUAndP(@Param("username") String username,@Param("password") String password);

    /**
     * 根据id查询一个用户
     * @param id 用户id
     * @return 对应的用户对象
     */
    UserInfo getUserById(Integer id);

    /**
     * 更新最后登录时间
     * @param date 当前系统时间
     */
    void lastReadCommentTime(Date date);
}
