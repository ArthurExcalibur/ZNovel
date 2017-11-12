package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.dao.UserDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.TextUtil;

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
    public boolean updateResID(int id, String resID) {
        String sql = "update user set resId = " + TextUtil.fillValue(resID) + " where id = " + id;
        return executeSQL(sql,null) > 0;
    }


}
