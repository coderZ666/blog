package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.MessageDao;
import com.zwx.domain.Message;
import com.zwx.utils.BeanUtils;
import com.zwx.utils.SensitiveWord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-10 21:28
 */
@Service
public class MessageServiceImp implements MessageService {

    @Resource
    private MessageDao messageDao;

    @Override
    public List<Message> listMessage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return messageDao.listMessage();
    }

    @Override
    public void saveMessage(Message message) {
        System.out.println(message.getContent());
        SensitiveWord sw = (SensitiveWord) BeanUtils.getBean("sensitive");
        //昵称过滤
        message.setNickname(sw.filterInfo(message.getNickname()));
        //留言内容过滤
        message.setContent(sw.filterInfo(message.getContent()));
        if (sw.getSensitiveWordSet().size()>0){//如果有敏感词
            //将敏感词保存
            message.setSensitiveWord(sw.getSensitiveWordSet().toString());
        }
        messageDao.saveMessage(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        //删除父留言和子留言
        messageDao.deleteMessageById(id);//id=#{id}
        messageDao.deleteMessageByParentId(id);//parentId=#{id}
    }

    @Override
    public boolean messageOpen() {
        Integer i = messageDao.messageOpen();
        return i==0;
    }

    @Override
    public Message getMessageById(Integer id) {
        return messageDao.getMessageById(id);
    }


    @Override
    public List<Message> listSensitiveMessage() {
        return messageDao.listSensitiveMessage();
    }

    @Override
    public Integer deleteSensitiveMessage() {
        //先删除所有含敏感词留言的子留言
        Integer a = messageDao.deleteSensitiveMessageChildren();
        //再删除所有含敏感词的留言
        Integer b = messageDao.deleteSensitiveMessage();
        return a+b;
    }

    @Override
    public void openMessage() {
        messageDao.openMessage();
    }

    @Override
    public void closeMessage() {
        messageDao.closeMessage();
    }

    @Override
    public Integer updateSensitiveMessage(Message message) {
        return messageDao.updateSensitiveMessage(message);
    }


}
