package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.Comment;

import java.util.List;

public interface CommentDao {

    boolean addComment(int type,String comment,int pinglunId,int beipingId,int huifuId,int star,String name,String img,String beipingName);
    boolean removeComment(int id);

    List<Comment> getCommentList(int pinglunId, int beipingId,int page,int type);

    int getCommentNumber(int pinglunId,int beipingId,int type);
}
