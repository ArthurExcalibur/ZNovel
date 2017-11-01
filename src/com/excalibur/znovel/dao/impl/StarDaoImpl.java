package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.dao.StarDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StarDaoImpl extends BaseDao implements StarDao {

    @Override
    public boolean star(int zanId, int beizanId,int type) {
        String sql = "select * from dianzan where beizanId = " + beizanId + " and dianzanId = " + zanId;
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs && rs.next()){
                String s = "delete from dianzan where beizanId = " + beizanId + " and dianzanId = " + zanId;
                return executeSQL(s,null) > 0;
            }else{
                String s = "insert into dianzan(type,dianzanId,beizanId,time) values(" +
                        type + "," +
                        zanId + "," +
                        beizanId + "," +
                        "'" + System.currentTimeMillis() + "')";
                return executeSQL(s,null) > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return false;
    }

    @Override
    public int getStarNumber(int id) {
        String sql = "select count(*) from dianzan where beizanId = " + id;
        return executeCountSQL(sql);
    }
}
