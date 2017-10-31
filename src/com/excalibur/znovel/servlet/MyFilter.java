package com.excalibur.znovel.servlet;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter implements javax.servlet.Filter {

    private FilterConfig config = null;

    public void destroy() {
        System.out.println("MyCharsetFilter准备销毁...");
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp,
                         javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        // 强制类型转换
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //获取web.xm设置的编码集，设置到Request、Response中
        request.setCharacterEncoding(config.getInitParameter("charset"));
        response.setContentType(config.getInitParameter("contentType"));
        response.setCharacterEncoding(config.getInitParameter("charset"));
        //将请求转发到目的地
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws javax.servlet.ServletException {
        this.config = config;
        System.out.println("MyCharsetFilter初始化...");
    }

}
