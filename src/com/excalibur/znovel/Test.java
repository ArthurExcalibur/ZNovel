package com.excalibur.znovel;


import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.impl.BookDaoImpl;

import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args){
        BookDaoImpl dao = new BookDaoImpl();
        List<Book> list = dao.getBookByAuthor("跳舞",0);
        for (Book b : list) {
            System.out.println(b.toString());
        }
//        System.out.println(System.currentTimeMillis());
//        Random random = new Random();
//        int number = random.nextInt(200000);
//        System.out.println(number);
//        System.out.println(System.currentTimeMillis());
    }

}
