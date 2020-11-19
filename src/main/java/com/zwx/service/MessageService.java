package com.zwx.service;

import com.zwx.domain.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 有关留言信息的业务逻辑层
 * @author coderZWX
 * @date 2020-11-10 21:24
 */
@Transactional
public interface MessageService {

    /**
     * 分页查询所有留言的方法
     * @param page 需要第几页
     * @param size 每页几条
     * @return 查到的留言集合
     */
    List<Message> listMessage(Integer page,Integer size);

    /**
     * 保存一条新留言的方法
     * @param message 封装新留言信息的对象
     */
    void saveMessage(Message message);

    /**
     * 根据id删除留言
     * @param id 留言id
     */
    void deleteMessage(Integer id);

    /**
     * 查询留言功能是否开启
     * @return 开启或未开启
     */
    boolean messageOpen();

    /**
     * 根据id查一条留言
     * @param id 留言id
     * @return 查到的留言对象
     */
    Message getMessageById(Integer id);

    /**
     * 查询所有含敏感词的留言
     * @return 含敏感词留言的集合
     */
    List<Message> listSensitiveMessage();

    /**
     * 删除所有含敏感词的留言
     * @return 删除的数量
     */
    Integer deleteSensitiveMessage();

    /**
     * 开启留言功能
     */
    void openMessage();

    /**
     * 关闭留言功能
     */
    void closeMessage();

    /**
     * 修改含敏感词的留言
     * @param message 修改后的留言对象
     * @return 修改成功的数量
     */
    Integer updateSensitiveMessage(Message message);
}
