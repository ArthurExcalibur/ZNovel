package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.dao.AdviceDao;
import com.excalibur.znovel.dao.BaseDao;

public class AdviceDaoImpl extends BaseDao implements AdviceDao {

    @Override
    public boolean addAdvice(String message, String phone, String image, String id) {
        String sql = "insert into advice(message,phone,image,uid) values ('"+
                message + "','"+
                phone + "','"+
                image + "','"+
                id + "')";
        return executeSQL(sql,null) > 0;
    }

}
