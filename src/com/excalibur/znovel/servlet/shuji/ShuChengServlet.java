package com.excalibur.znovel.servlet.shuji;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.impl.BookDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.data.ShuChengEntity;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShuChengServlet extends HttpServlet {

    private BookDao dao = new BookDaoImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> hotBooks = dao.getBookByReadNumber(0);
        List<Book> newBooks = dao.getBookByLastUpdate(0);
        List<Book> guessBooks = dao.getBooksByGuess();
        List<Book> geleiBooks = dao.getBooksByGeLei();
        ShuChengEntity entity = new ShuChengEntity();
        entity.setHot(gson.toJson(hotBooks));
        entity.setXinshu(gson.toJson(newBooks));
        entity.setGuess(gson.toJson(guessBooks));
        entity.setGelei(gson.toJson(geleiBooks));

        BaseEntity entity1 = new BaseEntity();
        entity1.setShijian(System.currentTimeMillis());
        if(hotBooks.size() > 0 && newBooks.size() > 0 && guessBooks.size() > 0 && geleiBooks.size() > 0){
            entity1.setStatus(true);
            entity1.setData(gson.toJson(entity));
        }else{
            entity1.setStatus(false);
            entity1.setError_info("内部错误");
        }
        response.getWriter().print(gson.toJson(entity1));

    }

}
