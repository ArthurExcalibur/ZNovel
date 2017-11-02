package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.History;
import com.excalibur.znovel.dao.HistoryDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.TextUtil;

import java.util.List;

public class HistoryDaoImpl extends BaseDao implements HistoryDao {

    @Override
    public boolean addHistory(int uid, int bid) {
        String sql = "insert into read_info(uid,bid,firstReadTime,lastReadTime,totalReadTime) values(" +
                uid + "," +
                bid + "," +
                "'" + System.currentTimeMillis() + "'," +
                "'" + System.currentTimeMillis() + "'," +
                "0)";
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean removeHistory(int id) {
        String sql = "update read_info set status = '不启用' where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean removeAllHistory(int uid) {
        String sql = "update read_info set status = '不启用' where uid = " + uid;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean updateHistory(int id,long addTime,String comment) {
        String sql = "update read_info set totalReadTime = '" + addTime + "',comment = " + TextUtil.fillValue(comment) + " where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public List<History> getHistoryList(int uid) {
        return null;
    }

}
