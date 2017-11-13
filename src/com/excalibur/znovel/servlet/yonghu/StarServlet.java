package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.dao.StarDao;
import com.excalibur.znovel.dao.impl.StarDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StarServlet extends HttpServlet {

    private Gson gson = new Gson();
    private StarDao dao = new StarDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        String action = request.getParameter("action");
        if(TextUtil.isEmpty(action)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            entity.setStatus(true);
            switch (action){
                case "number" :{
                    String i = request.getParameter("id");
                    String type = request.getParameter("type");
                    if(TextUtil.isEmpty(i) || TextUtil.isEmpty(type)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        entity.setData("" + dao.getStarNumber(Integer.parseInt(i),Integer.parseInt(type)));
                    }
                    break;
                }
                case "star" :{
                    String zanId = request.getParameter("zanId");
                    String beizanId = request.getParameter("beizanId");
                    String type = request.getParameter("type");
                    if(TextUtil.isEmpty(zanId) || TextUtil.isEmpty(beizanId) || TextUtil.isEmpty(type)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        entity.setData("" + dao.star(Integer.parseInt(zanId),Integer.parseInt(beizanId),Integer.parseInt(type)));
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
