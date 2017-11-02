package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.Comment;
import com.excalibur.znovel.dao.CommentDao;
import com.excalibur.znovel.dao.StarDao;
import com.excalibur.znovel.dao.base.BaseDao;
import com.excalibur.znovel.util.Constants;
import com.excalibur.znovel.util.TextUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl extends BaseDao implements CommentDao {

    private StarDao starDao = new StarDaoImpl();

    @Override
    public boolean addComment(int type, String comment, int pinglunId, int beipingId, int huifuId, int star, String name, String img) {
        String sql = "insert into pinglun(type,comment,pinglunId,beipingId,huifuId,time,star,name,img,status) values(" +
                type + "," +
                TextUtil.fillValue(comment) + "," +
                pinglunId + "," +
                beipingId + "," +
                huifuId + "," +
                "'" + System.currentTimeMillis() + "'," +
                star + "," +
                TextUtil.fillValue(name) + "," +
                TextUtil.fillValue(img) + "," +
                "'启用')";
        return executeSQL(sql,null) > 0;
    }

    @Override
    public boolean removeComment(int id) {
        String sql = "update pinglun set status = '不启用' where id = " + id;
        return executeSQL(sql,null) > 0;
    }

    @Override
    public List<Comment> getCommentList(int pinglunId, int beipingId, int page, int type) {
        page *= Constants.Book.PAGE_NUMBER;
        String sql;
        if(pinglunId >= 0){
            sql = "select * from pinglun where status = '启用' and huifuId = " + pinglunId + " and beipingId = " + beipingId + " and type = " + type + " limit " + page + "," + Constants.Book.PAGE_NUMBER;
        }else{
            sql = "select * from pinglun where status = '启用' and beipingId = " + beipingId + " and type = " + type + " limit " + page + "," + Constants.Book.PAGE_NUMBER;
        }
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs){
                List<Comment> list = new ArrayList<>();
                while (rs.next()){
                    Comment c = new Comment();
                    c.setId(rs.getInt("id"));
                    c.setType(rs.getInt("type"));
                    c.setComment(rs.getString("comment"));
                    c.setPinglunId(rs.getInt("pinglunId"));
                    c.setBeipingId(rs.getInt("beipingId"));
                    c.setHuifuId(rs.getInt("huifuId"));
                    c.setTime(rs.getString("time"));
                    c.setStar(rs.getInt("star"));
                    c.setName(rs.getString("name"));
                    c.setImg(rs.getString("img"));
                    c.setStatus(rs.getString("status"));
                    int number = getCommentNumber(c.getPinglunId(),beipingId,type + 1);
                    int number1 = starDao.getStarNumber(c.getPinglunId(),type);
                    c.setDianzanshu(number1);
                    c.setPinglunshu(number);
                    list.add(c);
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

    @Override
    public int getCommentNumber(int pinglunId, int beipingId, int type) {
        String sql;
        if(pinglunId >= 0){
            sql = "select count(*) from pinglun where huifuId = " + pinglunId + " and beipingId = " + beipingId + " and type = " + type + " where status = '启用'";
        }else{
            sql = "select count(*) from pinglun where beipingId = " + beipingId + " and type = " + type + " where status = '启用'";
        }
        return executeCountSQL(sql);
    }
}
