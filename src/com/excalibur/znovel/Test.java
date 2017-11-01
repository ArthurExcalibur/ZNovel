package com.excalibur.znovel;


import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.bean.Message;
import com.excalibur.znovel.dao.AdviceDao;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.MessageDao;
import com.excalibur.znovel.dao.StarDao;
import com.excalibur.znovel.dao.impl.AdviceDaoImpl;
import com.excalibur.znovel.dao.impl.BookDaoImpl;
import com.excalibur.znovel.dao.impl.MessageDaoImpl;
import com.excalibur.znovel.dao.impl.StarDaoImpl;

import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args){
//        BookDaoImpl dao = new BookDaoImpl();
//        List<Book> list = dao.getBookByAuthor("跳舞",0);
//        for (Book b : list) {
//            System.out.println(b.toString());
//        }
//        System.out.println(System.currentTimeMillis());
//        Random random = new Random();
//        int number = random.nextInt(200000);
//        System.out.println(number);
//        System.out.println(System.currentTimeMillis());
//        AdviceDao dao = new AdviceDaoImpl();
//        dao.addAdvice("message",null,null,null);
//        MessageDao dao = new MessageDaoImpl();
//        dao.readAllMessage(2);
//        List<Message> list = dao.getMessageByTypeAndStatus(-1,-2,0);
//        for (Message m : list) {
//            System.out.println(dao.getUnReadMessageNumber(2));
//        }
        StarDao dao = new StarDaoImpl();
        System.out.println(dao.star(1,1,0));
    }

}
