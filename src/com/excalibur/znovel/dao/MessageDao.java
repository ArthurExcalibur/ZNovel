package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.Message;

import java.util.List;

public interface MessageDao {

    int getUnReadMessageNumber(int uid);

    boolean readMessage(int id);
    boolean readAllMessage(int uid);

    List<Message> getMessageByTypeAndStatus(int type,int isRead,int page);
}
