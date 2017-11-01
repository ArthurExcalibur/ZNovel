package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.Message;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.dao.MessageDao;
import com.excalibur.znovel.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl extends BaseDao implements MessageDao {

    @Override
    public int getUnReadMessageNumber(int uid) {
        String sql = "select count(*) from message where status = '启用' and isRead = 1 and toUid = " + uid;
        return executeCountSQL(sql);
    }

    @Override
    public boolean readMessage(int id) {
        String sql = "update message set isRead = 2,readTime = '" + System.currentTimeMillis() + "' where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean readAllMessage(int uid) {
        String sql = "update message set isRead = 2,readTime = '" + System.currentTimeMillis() + "' where status = '启用' and toUid = " + uid;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public List<Message> getMessageByTypeAndStatus(int type, int isRead,int page) {
        page *= Constants.Book.PAGE_NUMBER;
        String sql;
        if(type > 0){
            if(isRead > 0){
                sql = "select * from message where type =" + type + " and isRead =" + isRead + " and status = '启用' limit " + page + "," + Constants.Book.PAGE_NUMBER;
            }else{
                sql = "select * from message where type =" + type + " and status = '启用' limit " + page + "," + Constants.Book.PAGE_NUMBER;
            }
        }else{
            if(isRead > 0){
                sql = "select * from message where isRead =" + isRead + " and status = '启用' limit " + page + "," + Constants.Book.PAGE_NUMBER;
            }else{
                return null;
            }
        }
        return getMessageBySQL(sql);
    }

    private List<Message> getMessageBySQL(String sql){
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs){
                List<Message> list = new ArrayList<>();
                while (rs.next()){
                    list.add(getMessage(rs));
                }
                return list;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return null;
    }

    private Message getMessage(ResultSet rs){
        Message message = new Message();
        try {
            message.setId(rs.getInt("id"));
            message.setMessage(rs.getString("message"));
            message.setFromUid(rs.getInt("fromUid"));
            message.setToUid(rs.getInt("toUid"));
            message.setIsRead(rs.getInt("isRead"));
            message.setType(rs.getInt("type"));
            message.setReadTime(rs.getString("readTime"));
            message.setSendTime(rs.getString("sendTime"));
            message.setStatus(rs.getString("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
}
