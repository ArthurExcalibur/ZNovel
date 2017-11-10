package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.bean.History;
import com.excalibur.znovel.dao.HistoryDao;
import com.excalibur.znovel.dao.impl.HistoryDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class LiShiServlet extends HttpServlet {

    private Gson gson = new Gson();
    private HistoryDao dao = new HistoryDaoImpl();

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
                case "update":{
                    History h = gson.fromJson(request.getParameter("p"),History.class);
                    if("add".equals(action)){
                        if(!dao.addHistory(h)){
                            entity.setStatus(false);
                            entity.setError_info("内部错误");
                        }
                    }else{
                        if(!dao.updateHistory(h)){
                            entity.setStatus(false);
                            entity.setError_info("内部错误");
                        }
                    }
                    break;
                }
                case "remove":
                case "removeAll":{
                    String id = request.getParameter("id");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if("remove".equals(action)){
                            if(!dao.removeHistory(Integer.parseInt(id))){
                                entity.setStatus(false);
                                entity.setError_info("内部错误");
                            }
                        }else{
                            if(!dao.removeAllHistory(Integer.parseInt(id))){
                                entity.setStatus(false);
                                entity.setError_info("内部错误");
                            }
                        }
                    }
                    break;
                }
                case "list":{
                    String id = request.getParameter("id");
                    String page = request.getParameter("page");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        int p = TextUtil.isEmpty(page) ? 0 : Integer.parseInt(page);
                        List<History> list = dao.getHistoryList(Integer.parseInt(id),p);
                        entity.setData(gson.toJson(list));
                    }
                    break;
                }
                case "commit":{
                    Type type = new TypeToken<List<History>>(){}.getType();
                    List<History> list = gson.fromJson(request.getParameter("p"),type);
                    if(!dao.commitHistory(list)){
                        entity.setStatus(false);
                        entity.setError_info("内部错误");
                    }
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
