package com.excalibur.znovel.servlet.filter;

import com.excalibur.znovel.dao.UserDao;
import com.excalibur.znovel.dao.impl.UserDaoImpl;
import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.TextUtil;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YonghuFilter implements Filter {

    private UserDao dao = new UserDaoImpl();
    private Gson gson = new Gson();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强制类型转换
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        String id = request.getHeader("id");
        String resID = request.getHeader("resID");

        if(TextUtil.isEmpty(id)){
            entity.setStatus(false);
            entity.setError_info("该用户不存在");
            response.getWriter().print(gson.toJson(entity));
        }else{
            if(dao.checkForResID(Integer.parseInt(id),resID)){
                entity.setStatus(false);
                entity.setError_info("手机序列号不对");
                response.getWriter().print(gson.toJson(entity));
            }else{
                //将请求转发到目的地
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
