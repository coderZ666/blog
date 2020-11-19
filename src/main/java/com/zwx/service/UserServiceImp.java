package com.zwx.service;

import com.zwx.dao.UserDao;
import com.zwx.domain.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author coderZWX
 * @date 2020-11-05 15:27
 */
@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserInfo checkUser(String username, String password) {
        return userDao.getUserByUAndP(username,password);
    }

    @Override
    public void lastReadCommentTime() {
        //获取当前系统时间
        Date date = new Date();
        userDao.lastReadCommentTime(date);
    }

}
