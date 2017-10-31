package com.excalibur.znovel.servlet;

import com.excalibur.znovel.dao.AdviceDao;
import com.excalibur.znovel.dao.impl.AdviceDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YiJianServlet extends HttpServlet {

    private Gson gson = new Gson();
    private AdviceDao dao = new AdviceDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        if(TextUtil.isEmpty(message)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            String phone = request.getParameter("phone");
            String image = request.getParameter("image");
            String id = request.getParameter("id");
            if(dao.addAdvice(message,phone,image,id)){
                entity.setStatus(true);
            }else {
                entity.setStatus(false);
                entity.setError_info("内部错误");
            }
        }

        response.getWriter().print(gson.toJson(entity));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
