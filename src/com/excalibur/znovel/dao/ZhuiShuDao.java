package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.Book;

import java.util.List;

public interface ZhuiShuDao {

    boolean addZhuiShu(int uid,int bid);

    boolean removeZhuiShu(int uid,int bid);

    List<Book> getZhuiShuList(int uid,int page);
}
