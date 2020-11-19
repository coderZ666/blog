package com.zwx.dao;

import com.zwx.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言表的数据访问层
 * @author coderZWX
 * @date 2020-11-10 21:33
 */
@Mapper
public interface MessageDao {

    /**
     * 查询所有留言，按父子评论关系封装
     * @return 查到的集合
     */
    List<Message> listMessage();

    /**
     * 保存一条新的留言
     * @param message 封装新留言信息的对象
     */
    void saveMessage(Message message);

    /**
     * 查询留言数量
     * @return 留言数量
     */
    Integer countMessage();

    /**
     * 根据id删除留言
     * @param id 留言id
     */
    void deleteMessageById(Integer id);

    /**
     * 根据父id删除子留言
     * @param id 父id
     */
    void deleteMessageByParentId(Integer id);

    /**
     * 查询留言功能是否开启
     * @return 返回0开启或1关闭
     */
    Integer messageOpen();

    /**
     * 查询所有含敏感词的留言
     * @return 含敏感词留言的集合
     */
    List<Message> listSensitiveMessage();

    /**
     * 删除所有含敏感词的留言的子留言
     * @return 删除的数量
     */
    Integer deleteSensitiveMessageChildren();

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

    /**
     * 根据id查询一条留言
     * @param id 留言id
     * @return 查到的留言对象
     */
    Message getMessageById(Integer id);
}
