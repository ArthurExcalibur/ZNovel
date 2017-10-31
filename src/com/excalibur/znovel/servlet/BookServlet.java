package com.excalibur.znovel.servlet;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.impl.BookDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {

    private BookDao dao = new BookDaoImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String value = request.getParameter("value");
        String page = request.getParameter("page");

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());
        if(TextUtil.isEmpty(action) || TextUtil.isEmpty(value)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            entity.setStatus(true);
            int p = TextUtil.isEmpty(page) ? 0 : Integer.parseInt(page);
            switch (action) {
                case "search": {
                    List<Book> list = dao.getBooks(value, p);
                    entity.setData(gson.toJson(list));
                    break;
                }
                case "name": {
                    List<Book> list = dao.getBookByName(value, p);
                    entity.setData(gson.toJson(list));
                    break;
                }
                case "author": {
                    List<Book> list = dao.getBookByAuthor(value, p);
                    entity.setData(gson.toJson(list));
                    break;
                }
                case "textNumber":{
                    List<Book> list = dao.getBookByTextNumber(p);
                    entity.setData(gson.toJson(list));
                    break;
                }default:{
                    entity.setStatus(false);
                    entity.setError_info("参数错误");
                }
            }
        }
        response.getWriter().print(gson.toJson(entity));
    }

}
