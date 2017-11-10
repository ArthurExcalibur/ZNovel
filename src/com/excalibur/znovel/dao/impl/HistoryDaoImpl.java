package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.bean.History;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.HistoryDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.Constants;
import com.excalibur.znovel.util.TextUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDaoImpl extends BaseDao implements HistoryDao {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean addHistory(History history) {
        String sql = "insert into read_info(uid,bid,firstReadTime,lastReadTime,totalReadTime,status,zhangjie,zhangjieIndex,precent) values(" +
                history.getUid() + "," +
                history.getBid() + "," +
                "'" + history.getFirstReadTime() + "'," +
                "'" + history.getLastReadTime() + "'," +
                "'" + history.getTotalReadTime() + "'," +
                "'" + history.getStatus() + "'," +
                history.getZhangjie() + "," +
                history.getZhangjieIndex() + "," +
                "'" + history.getPercent() + ")";
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
    public boolean updateHistory(History history) {
        int id = isHistoryExists(history.getUid(),history.getBid());
        if(id < 0)
            return false;
        String sql = "update read_info set lastReadTime = '" + history.getLastReadTime() +
                "',totalReadTime = '" + history.getTotalReadTime() +
                "',comment = " + TextUtil.fillValue(history.getComment())
                + ",zhangjie = " + history.getZhangjie() + ","
                + "zhangjieIndex = " + history.getZhangjieIndex() + ","
                + "percent = " + TextUtil.fillValue(history.getPercent())
                + " where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean commitHistory(List<History> list) {
        for (History h : list) {
            int id = isHistoryExists(h.getUid(),h.getBid());
            if (id >= 0) {
                if (!updateHistory(h)) {
                    return false;
                }
            } else {
                if (!addHistory(h)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<History> getHistoryList(int uid,int page) {
        page *= Constants.Book.PAGE_NUMBER;
        String sql = "select * from read_info where uid = " + uid + " and status = '启用' order by lastReadTime desc limit " + page + "," + Constants.Book.PAGE_NUMBER;
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs){
                List<History> list = new ArrayList<>();
                while (rs.next()){
                    History h = new History();
                    h.setId(rs.getInt("id"));
                    h.setUid(rs.getInt("uid"));
                    h.setBid(rs.getInt("bid"));
                    h.setComment(rs.getString("comment"));
                    h.setFirstReadTime(rs.getString("firstReadTime"));
                    h.setLastReadTime(rs.getString("lastReadTime"));
                    h.setTotalReadTime(rs.getString("totalReadTime"));
                    h.setStatus(rs.getString("status"));
                    h.setZhangjie(rs.getInt("zhangjie"));
                    h.setZhangjieIndex(rs.getInt("zhangjieIndex"));
                    h.setPercent(rs.getString("percent"));

                    Book b = bookDao.getBookInfo(rs.getInt("bid"));
                    h.setDesc(b.getDescription());
                    h.setAuthor(b.getAuthor());
                    h.setImg(b.getImg());
                    h.setName(b.getName());

                    list.add(h);
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

    private int isHistoryExists(int uid,int bid){
        String sql = "select id from read_info where uid = " + uid + " and bid = " + bid;
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs && rs.next()){
                return rs.getInt("id");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return -1;
    }
}
