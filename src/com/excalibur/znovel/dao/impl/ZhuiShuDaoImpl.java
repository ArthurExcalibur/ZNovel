package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.ZhuiShuDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZhuiShuDaoImpl extends BaseDao implements ZhuiShuDao {

    @Override
    public boolean addZhuiShu(int uid,int bid) {
        String sql = "insert into zhuishu(uid,bid,time,status) values(" +
                uid + "," +
                bid + "," +
                "'" + System.currentTimeMillis() + "'," +
                "'启用')";
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean removeZhuiShu(int uid, int bid) {
        String sql = "update zhuishu set status = '不启用' where uid = " + uid + " and bid = " + bid;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public List<Book> getZhuiShuList(int uid,int page) {
        page *= Constants.Book.PAGE_NUMBER;
        String sql = "select bid from zhuishu where status = '启用' and uid = " + uid + " limit " + page + "," + Constants.Book.PAGE_NUMBER;
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            List<Integer> bidList = new ArrayList<>();
            if(null != rs){
                while (rs.next()){
                    bidList.add(rs.getInt("bid"));
                }
            }
            List<Book> list = new ArrayList<>();
            BookDao dao = new BookDaoImpl();
            for (int i = 0;i < bidList.size();i++) {
                list.add(dao.getBookInfo(i));
            }
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return null;
    }

}
