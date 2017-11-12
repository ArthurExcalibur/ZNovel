package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.dao.UserDao;
import com.excalibur.znovel.dao.impl.UserDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserDao dao = new UserDaoImpl();
    private Gson gson = new Gson();

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
                case "register":{
                    String name = request.getParameter("name");
                    String img = request.getParameter("img");
                    String desc = request.getParameter("desc");
                    String pass = request.getParameter("pass");
                    String sex = request.getParameter("sex");
                    String resID = request.getParameter("resID");
                    if(TextUtil.isEmpty(name) || TextUtil.isEmpty(pass) || TextUtil.isEmpty(resID)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if(dao.checkForUser(name)){
                            entity.setStatus(false);
                            entity.setError_info("改用户名已存在");
                        }else{
                            if(!dao.register(name,pass,img,desc,sex,resID)){
                                entity.setStatus(false);
                                entity.setError_info("注册失败");
                            }
                        }
                    }
                    break;
                }
                case "login":{
                    String id = request.getParameter("id");
                    String pass = request.getParameter("pass");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if(dao.checkForPass(Integer.parseInt(id),pass)){
                            String resID = request.getHeader("resID");
                            dao.updateResID(Integer.parseInt(id),resID);
                        }else{
                            entity.setStatus(false);
                            entity.setError_info("用户名或密码错误");
                        }
                    }
                    break;
                }
                case "updatePass":{
                    String id = request.getParameter("id");
                    String nesAction = request.getParameter("newValue");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if(!dao.updatePass(Integer.parseInt(id),nesAction)){
                            entity.setStatus(false);
                            entity.setError_info("修改失败");
                        }
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
