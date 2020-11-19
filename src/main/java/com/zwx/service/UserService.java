package com.zwx.service;

import com.zwx.domain.UserInfo;

/**
 * 有关用户的服务层
 * @author coderZWX
 * @date 2020-11-05 13:56
 */
public interface UserService {

    /**
     * 验证用户登录的方法
     * @param username 用户名
     * @param password 密码
     * @return 返回查到的用户对象
     */
    UserInfo checkUser(String username,String password);

    /**
     * 更新最后登陆时间的方法
     */
    void lastReadCommentTime();
}
