package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.dao.AdviceDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.TextUtil;

public class AdviceDaoImpl extends BaseDao implements AdviceDao {

    @Override
    public boolean addAdvice(String message, String phone, String image, String id) {
        message = TextUtil.fillValue(message);
        phone = TextUtil.fillValue(phone);
        image = TextUtil.fillValue(image);
        String sql = "insert into advice(message,phone,image,uid,time,status) values ("+
                message + ","+
                phone + ","+
                image + ","+
                (TextUtil.isEmpty(id) ? null : Integer.parseInt(id)) + "," +
                "'" + System.currentTimeMillis() + "'," +
                "'启用')";
        return executeSQL(sql,null) > 0;
    }

}
