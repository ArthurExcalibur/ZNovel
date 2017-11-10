package com.excalibur.znovel.servlet.yonghu;

import com.excalibur.znovel.bean.Comment;
import com.excalibur.znovel.dao.CommentDao;
import com.excalibur.znovel.dao.impl.CommentDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommentServlet extends HttpServlet {

    private Gson gson = new Gson();
    private CommentDao dao = new CommentDaoImpl();

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
                case "add":{
                    String type = request.getParameter("type");//必须
                    String comment = request.getParameter("comment");//必须
                    String pinglunId = request.getParameter("pinglunId");//必须
                    String beipingId = request.getParameter("beipingId");//必须
                    String huifuId = request.getParameter("huifuId");//可选
                    String star = request.getParameter("star");//可选
                    String name = request.getParameter("name");//必须
                    String img = request.getParameter("img");//必须
                    String pingName = request.getParameter("pingName");//可选
                    if(TextUtil.isEmpty(type) || TextUtil.isEmpty(comment) || TextUtil.isEmpty(pinglunId) || TextUtil.isEmpty(beipingId)
                            || TextUtil.isEmpty(name) || TextUtil.isEmpty(img)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        int h = TextUtil.isEmpty(huifuId) ? -1 : Integer.parseInt(huifuId);
                        if(! dao.addComment(Integer.parseInt(type),comment,Integer.parseInt(pinglunId),Integer.parseInt(beipingId)
                                ,h,Integer.parseInt(star),name,img,pingName)){
                            entity.setStatus(false);
                            entity.setError_info("内部错误");
                        }
                    }
                    break;
                }
                case "remove":{
                    String id = request.getParameter("id");
                    if(TextUtil.isEmpty(id)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        if(!dao.removeComment(Integer.parseInt(id))){
                            entity.setStatus(false);
                            entity.setError_info("该评论不存在");
                        }
                    }
                    break;
                }
                case "list":{
                    String type = request.getParameter("type");//必须
                    String pinglunId = request.getParameter("pinglunId");//可选
                    String beipingId = request.getParameter("beipingId");//必须
                    if(TextUtil.isEmpty(type) || TextUtil.isEmpty(beipingId)){
                        entity.setStatus(false);
                        entity.setError_info("参数错误");
                    }else{
                        int h = TextUtil.isEmpty(pinglunId) ? -1 : Integer.parseInt(pinglunId);
                        List<Comment> list = dao.getCommentList(h,Integer.parseInt(beipingId),h,Integer.parseInt(type));
                        entity.setData(gson.toJson(list));
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
