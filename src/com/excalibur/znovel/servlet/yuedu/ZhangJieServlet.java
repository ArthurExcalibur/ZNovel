package com.excalibur.znovel.servlet.yuedu;

import com.excalibur.znovel.bean.ZhangJie;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.excalibur.znovel.util.ZhangJieUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ZhangJieServlet extends HttpServlet {

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String action = request.getParameter("action");

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        if(TextUtil.isEmpty(url) || TextUtil.isEmpty(action)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            entity.setStatus(true);
            switch (action){
                case "index":{
                    List<ZhangJie> list = ZhangJieUtil.getContentByIndexUrl(url);
                    entity.setData(gson.toJson(list));
                    break;
                }
                case "zhangjie":{
                    String content = ZhangJieUtil.getContentByUrl(url);
                    entity.setData(content);
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
