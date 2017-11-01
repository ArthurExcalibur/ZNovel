package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.bean.Message;
import com.excalibur.znovel.dao.MessageDao;
import com.excalibur.znovel.dao.impl.MessageDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MessageServlet extends HttpServlet {

    private Gson gson = new Gson();
    private MessageDao dao = new MessageDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        if(TextUtil.isEmpty(action)){
            entity.setStatus(false);
            entity.setError_info("参数错误");
        }else{
            entity.setStatus(true);
            switch (action){
                case "number":{
                    String id = request.getParameter("id");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        entity.setData("" + dao.getUnReadMessageNumber(Integer.parseInt(id)));
                    }
                    break;
                }
                case "message":{
                    String p = request.getParameter("page");
                    String t = request.getParameter("type");
                    String r = request.getParameter("isRead");
                    int limit = TextUtil.isEmpty(p) ? 0 : Integer.parseInt(p);
                    int type = TextUtil.isEmpty(t) ? -1 : Integer.parseInt(t);
                    int read = TextUtil.isEmpty(r) ? -1 : Integer.parseInt(r);
                    List<Message> list = dao.getMessageByTypeAndStatus(type,read,limit);
                    entity.setData(gson.toJson(list));
                    break;
                }
                case "read":{
                    String range = request.getParameter("range");
                    String id = request.getParameter("id");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if("single".equals(range)){
                            if(!dao.readMessage(Integer.parseInt(id))) {
                                entity.setStatus(false);
                                entity.setError_info("信息不存在");
                            }
                        }else if("all".equals(range)){
                            if(!dao.readAllMessage(Integer.parseInt(id))){
                                entity.setStatus(false);
                                entity.setError_info("用户不存在");
                            }
                        }else{
                            entity.setStatus(false);
                            entity.setError_info("参数错误");
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
