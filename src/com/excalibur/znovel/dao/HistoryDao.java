package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.History;

import java.util.List;

public interface HistoryDao {
    boolean addHistory(int uid,int bid);
    boolean removeHistory(int id);
    boolean removeAllHistory(int uid);
    boolean updateHistory(int id,long addTime,String comment);
    List<History> getHistoryList(int uid);
}
