package com.zwx.service;

import com.github.pagehelper.PageHelper;
import com.zwx.dao.CommentDao;
import com.zwx.domain.Comment;
import com.zwx.utils.BeanUtils;
import com.zwx.utils.SensitiveWord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author coderZWX
 * @date 2020-11-07 14:43
 */
@Service
public class CommentServiceImp implements CommentService {

    @Resource
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentsByBlogId(Integer blogId, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return commentDao.getCommentsByBlogId(blogId);
    }

    @Override
    public int saveComment(Comment comment) {
        SensitiveWord sw = (SensitiveWord) BeanUtils.getBean("sensitive");
        //昵称过滤
        comment.setNickname(sw.filterInfo(comment.getNickname()));
        //评论内容过滤
        comment.setContent(sw.filterInfo(comment.getContent()));
        if (sw.getSensitiveWordSet().size()>0){//如果有敏感词
            //将敏感词保存起来
            comment.setSensitiveWord(sw.getSensitiveWordSet().toString());
        }
        return commentDao.saveComment(comment);
    }

    @Override
    public Integer countCommentsByBlogId(Integer blogId) {
        return commentDao.countCommentsByBlogId(blogId);
    }

    @Override
    public void deleteComment(Integer id) {
        //删除父评论及子评论
        commentDao.deleteCommentById(id);//id=#{id}
        commentDao.deleteCommentByParentId(id);//parentId=#{id}
    }

    @Override
    public List<Comment> listSensitiveComment() {
        return commentDao.listSensitiveComment();
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public Integer updateSensitiveComment(Comment comment) {
        return commentDao.updateSensitiveComment(comment);
    }

    @Override
    public Integer deleteSensitiveComment() {
        //先删除所有含敏感词评论的子评论
        Integer a = commentDao.deleteSensitiveCommentChildren();
        //再删除所有含敏感词的评论
        Integer b = commentDao.deleteSensitiveComment();
        return a+b;
    }

    @Override
    public void closeComment() {
        commentDao.closeComment();
    }

    @Override
    public void openComment() {
        commentDao.openComment();
    }

    @Override
    public List<Comment> listUnreadComment() {
        return commentDao.listUnreadComment();
    }

    @Override
    public void toTopComment(Integer id) {
        commentDao.toTopComment(id);
    }

    @Override
    public List<Comment> getTopCommentsByBlogId(Integer blogId) {
        return commentDao.getTopCommentsByBlogId(blogId);
    }

    @Override
    public void cancelTopComment(Integer id) {
        commentDao.cancelTopComment(id);
    }
}
