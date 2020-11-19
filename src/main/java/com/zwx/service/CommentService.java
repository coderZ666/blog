package com.zwx.service;

import com.zwx.domain.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 有关评论的业务逻辑层
 * @author coderZWX
 * @date 2020-11-07 14:42
 */
@Transactional
public interface CommentService {

    /**
     * 根据博客id查询所有该博客下的评论
     * @param blogId 博客id
     * @return 查到的评论集合
     */
    List<Comment> getCommentsByBlogId(Integer blogId, Integer page, Integer size);

    /**
     * 新增一条评论信息
     * @param comment 封装评论信息的对象
     * @return 返回新增成功的数量
     */
    int saveComment(Comment comment);

    /**
     * 根据博客id查询该博客下评论的数量
     * @param blogId 博客id
     * @return 该博客的评论数量
     */
    Integer countCommentsByBlogId(Integer blogId);

    /**
     * 根据id删除评论
     * @param id 评论id
     */
    void deleteComment(Integer id);

    /**
     * 查询所有含敏感词的评论
     * @return 查到的评论集合
     */
    List<Comment> listSensitiveComment();

    /**
     * 根据id查询一条评论
     * @param id 评论id
     * @return 查到的评论对象
     */
    Comment getCommentById(Integer id);

    /**
     * 修改敏感评论
     * @param comment 修改后的评论信息对象
     * @return 修改成功的数量
     */
    Integer updateSensitiveComment(Comment comment);

    /**
     * 删除所有敏感评论
     * @return 删除的数量
     */
    Integer deleteSensitiveComment();

    /**
     * 关闭所有博客的评论功能
     */
    void closeComment();

    /**
     * 开启所有博客的评论功能
     */
    void openComment();

    /**
     * 查询所有未读评论
     * @return 未读评论集合
     */
    List<Comment> listUnreadComment();

    /**
     * 根据id将评论设置为置顶
     * @param id 评论id
     */
    void toTopComment(Integer id);

    /**
     * 根据博客id查询所有的置顶评论
     * @param blogId 博客id
     * @return 置顶评论集合
     */
    List<Comment> getTopCommentsByBlogId(Integer blogId);

    /**
     * 根据id将评论取消置顶
     * @param id 评论id
     */
    void cancelTopComment(Integer id);
}
