package com.excalibur.znovel.dao.impl;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BaseDao;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public Book getBookInfo(int id) {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        String sql = "select * from b_info where id = '" + id + "'";
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs && rs.next()){
                return parseBook(rs);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,state,rs);
        }
        return null;
    }

    @Override
    public List<Book> getBooks(String value, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where name like '%" + value
                + "%' or description like '%" + value
                + "%' or type like '%" + value
                + "%' or author like '%" + value
                + "%' or tags like '%" + value
                +  "%' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByName(String name, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where name like '%" + name + "%' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByType(String type, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where type = '" + type + "' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByAuthor(String author, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where author like '%" + author + "%' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByDesc(String desc, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where description like '%" + desc + "%' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByStatus(String status, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where status = '" + status + "' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByTag(String tag, int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info where tags like '%" + tag + "%' limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByTextNumber(int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info order by textNumber desc limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByLastUpdate(int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info order by lastUpdate desc limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByReadNumber(int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info order by readNumber desc limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBookByTodayUpdate(int limit) {
        limit = limit * Constants.Book.PAGE_NUMBER;
        String sql = "select * from b_info order by todayUpdate desc limit " + limit + "," + Constants.Book.PAGE_NUMBER;
        return getBookBySQL(sql);
    }

    @Override
    public List<Book> getBooksByGuess() {
//        List<Book> list = new ArrayList<>();
//        int max = getMaxId();
//        for(int i = 0;i < Integer.MAX_VALUE;i++){
//            Book b = getBookInfo((int) (Math.random() * max));
//            if(!list.contains(b)){
//                list.add(b);
//            }
//            if(list.size() == 10){
//                break;
//            }
//        }
//        return list;
        return getBooksByGeLei();
    }

    @Override
    public List<Book> getBooksByGeLei() {
        int max = getMaxId(true);
        int min = getMaxId(false);
        String str = "";
        Set<Integer> list = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int number = random.nextInt(max);
            if(number >= min){
                list.add(number);
            }
            if(list.size() == 10)
                break;
        }
        for (Integer aList : list) {
            str += (aList + ",");
        }
        str = str.substring(0,str.length() - 1);
        String sql = "select * from b_info where id in (" + str + ")";
        return getBookBySQL(sql);
    }

    private int getMaxId(boolean max){
        String sql;
        if(max){
            sql = "select id from b_info order by id desc limit 0,1";
        }else{
            sql = "select id from b_info order by id asc limit 0,1";
        }
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

    private List<Book> getBookBySQL(String sql) {
        Connection conn = null;
        PreparedStatement state = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            state = conn.prepareStatement(sql);
            rs = state.executeQuery();
            if(null != rs){
                List<Book> list = new ArrayList<>();
                while (rs.next()){
                    list.add(parseBook(rs));
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

    /**
     *  private int id;
     private String name;
     private String url;
     private String img;
     private String description;
     private int status;
     private String type;
     private String author;
     private int textNumber;
     private int readNumber;
     private int lastUpdate;
     private String download;
     private String indexUrl;
     private int todayUpdate;
     private String tags;
     private String newZhangJie;
     * @return
     */
    private Book parseBook(ResultSet rs){
        Book book = new Book();
        try {
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setUrl(rs.getString("url"));
            book.setImg(rs.getString("img"));
            book.setDescription(rs.getString("description"));
            book.setStatus(rs.getInt("status"));
            book.setType(rs.getString("type"));
            book.setAuthor(rs.getString("author"));
            book.setTextNumber(rs.getInt("textNumber"));
            book.setReadNumber(rs.getInt("readNumber"));
            book.setLastUpdate(rs.getInt("lastUpdate"));
            book.setDownload(rs.getString("download"));
            book.setIndexUrl(rs.getString("indexUrl"));
            book.setTodayUpdate(rs.getInt("todayUpdate"));
            book.setTags(rs.getString("tags"));
            book.setNewZhangJie(rs.getString("newZhangJie"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
