package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.bean.Book;
import com.excalibur.znovel.dao.BookDao;
import com.excalibur.znovel.dao.ZhuiShuDao;
import com.excalibur.znovel.dao.impl.BookDaoImpl;
import com.excalibur.znovel.dao.impl.ZhuiShuDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ZhuishuServlet extends HttpServlet {

    private Gson gson = new Gson();
    private ZhuiShuDao dao = new ZhuiShuDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        String action = request.getParameter("action");
        if(TextUtil.isEmpty(action)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            entity.setStatus(true);
            switch (action){
                case "add":
                case "remove" :{
                    String uid = request.getParameter("uid");
                    String bid = request.getParameter("bid");
                    if(TextUtil.isEmpty(uid) || TextUtil.isEmpty(bid)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if("add".equals(action)){
                            if(!dao.addZhuiShu(Integer.parseInt(uid),Integer.parseInt(bid))){
                                entity.setStatus(false);
                                entity.setError_info("内部错误");
                            }
                        }else{
                            if(!dao.removeZhuiShu(Integer.parseInt(uid),Integer.parseInt(bid))){
                                entity.setStatus(false);
                                entity.setError_info("内部错误");
                            }
                        }
                    }
                    break;
                }
                case "list":{
                    String uid = request.getParameter("uid");
                    if(TextUtil.isEmpty(uid)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        String page = request.getParameter("page");
                        List<Book > list = dao.getZhuiShuList(Integer.parseInt(uid),TextUtil.isEmpty(page) ? 0 : Integer.parseInt(page));
                        entity.setData(gson.toJson(list));
                    }
                    break;
                }
                case "number":{
                    String json = request.getParameter("list");
                    if(TextUtil.isEmpty(json)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        List<Integer> list = gson.fromJson(json, new TypeToken<List<Integer>>() {}.getType());
                        List<Boolean> rList = bookDao.isBookUpdated(list);
                        entity.setData(gson.toJson(rList));
                    }
                    break;
                }
                default:{
                    entity.setStatus(false);
                    entity.setError_info("参数错误");
                    break;
                }
            }
        }

        response.getWriter().print(gson.toJson(entity));
    }

}
