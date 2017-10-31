package com.excalibur.znovel.dao;

import com.excalibur.znovel.bean.Book;

import java.util.List;

public interface BookDao {

    Book getBookInfo(int id);

    List<Book> getBooks(String value,int limit);

    List<Book> getBookByName(String name, int limit);
    List<Book> getBookByType(String type, int limit);
    List<Book> getBookByAuthor(String author, int limit);
    List<Book> getBookByDesc(String desc, int limit);
    List<Book> getBookByStatus(String status, int limit);
    List<Book> getBookByTag(String tag, int limit);

    List<Book> getBookByTextNumber(int limit);
    List<Book> getBookByLastUpdate(int limit);
    List<Book> getBookByReadNumber(int limit);
    List<Book> getBookByTodayUpdate(int limit);

    List<Book> getBooksByGuess();
    List<Book> getBooksByGeLei();
}
