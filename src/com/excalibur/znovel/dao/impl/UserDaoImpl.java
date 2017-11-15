package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.dao.UserDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.TextUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao{

    @Override
    public boolean register(String name, String pass, String img, String desc, String sex, String resID) {
        String sql = "insert into user(pass,name,img,description,sex,time,resId) values("
                + TextUtil.fillValue(pass) + ","
                + TextUtil.fillValue(name) + ","
                + TextUtil.fillValue(img) + ","
                + TextUtil.fillValue(desc) + ","
                + TextUtil.fillValue(sex) + ","
                + "'" + System.currentTimeMillis() + "',"
                + TextUtil.fillValue(resID) + ")";
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean updatePass(int id, String newPass) {
        String sql = "update user set pass = " + TextUtil.fillValue(newPass) + " where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean checkForUser(String name) {
        String sql = "select count(*) from user where name = " + TextUtil.fillValue(name);
        return executeCountSQL(sql) > 0;
    }

    @Override
    public boolean checkForPass(int id, String pass) {
        String sql = "select count(*) from user where id = " + id + " and pass = " + TextUtil.fillValue(pass);
        return executeCountSQL(sql) > 0;
    }

    @Override
    public boolean checkForResID(int id, String resID) {
        String sql = "select count(*) from user where id = " + id + " and resId = " + TextUtil.fillValue(resID);
        return executeCountSQL(sql) > 0;
    }

    @Override
    public String updateResID(int id, String resID) {
        String s = "select resId from user where id = " + id;
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(s);
            rs = state.executeQuery();
            if(null != rs && rs.next()){
                String resId = rs.getString("resId");
                String sql = "update user set resId = " + TextUtil.fillValue(resID) + " where id = " + id;
                if(executeSQL(sql,null)> 0){
                    return resId;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return null;
    }


}
