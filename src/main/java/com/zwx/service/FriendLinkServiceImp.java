package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.FriendLinkDao;
import com.zwx.domain.FriendLink;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-11 16:09
 */
@Service
public class FriendLinkServiceImp implements FriendLinkService {

    @Resource
    private FriendLinkDao friendLinkDao;

    @Override
    public List<FriendLink> listFriendLink(Integer page,Integer size) {
        PageHelper.startPage(page,size);
        return friendLinkDao.listFriendLink();
    }

    @Override
    public boolean addressIsExist(String blogAddress) {
        Integer i = friendLinkDao.getFriendLinkCountByAddress(blogAddress);
        return i != 0;
    }

    @Override
    public int saveFriendLink(FriendLink friendLink) {
        return friendLinkDao.saveFriendLink(friendLink);
    }

    @Override
    public FriendLink getFriendLinkById(Integer id) {
        return friendLinkDao.getFriendLinkById(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        return friendLinkDao.updateFriendLink(friendLink);
    }

    @Override
    public void deleteFriendLink(Integer id) {
        friendLinkDao.deleteFriendLink(id);
    }

    @Override
    public List<FriendLink> listFriendLink() {
        return friendLinkDao.listFriendLink();
    }
}
