package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.History;

import java.util.List;

public interface HistoryDao {
    boolean addHistory(History history);
    boolean removeHistory(int id);
    boolean removeAllHistory(int uid);
    boolean updateHistory(History history);
    boolean commitHistory(List<History> list);
    List<History> getHistoryList(int uid,int page);
}
