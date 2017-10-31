package com.excalibur.znovel.servlet;

import com.excalibur.znovel.data.BaseEntity;
import com.excalibur.znovel.util.Constants;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FileServlet extends HttpServlet {

    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseEntity entity = new BaseEntity();
        entity.setShijian(System.currentTimeMillis());

        //得到上传文件的保存目录，将上传的文件存放于项目根目录的upload目录下
        String savePath = this.getServletContext().getRealPath("/upload");
        File file = new File(savePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            FileItem item = list.get(0);
            String filename = item.getName();
            if(filename == null || filename.trim().equals("")){
                entity.setStatus(true);
                entity.setError_info("内部错误");
            }else{
                filename = filename.substring(filename.lastIndexOf("\\")+1);
                InputStream in = item.getInputStream();
                FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                byte buffer[] = new byte[1024];
                int len;
                while((len=in.read(buffer))>0){
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
                //删除处理文件上传时生成的临时文件
                item.delete();
                entity.setStatus(true);
                entity.setData(Constants.ADDRESS + "upload/" + filename);
            }
//            for(FileItem item : list){
//                //如果fileitem中封装的是普通输入项的数据
//                if(item.isFormField()){
//                    String name = item.getFieldName();
//                    //解决普通输入项的数据的中文乱码问题
//                    String value = item.getString("UTF-8");
//                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
//                    System.out.println(name + "=" + value);
//                }else{//如果fileitem中封装的是上传文件
//                    //得到上传的文件名称，
//
//                }
//            }
        }catch (Exception e) {
            entity.setStatus(false);
            entity.setError_info("内部错误");
            e.printStackTrace();
        }

        response.getWriter().print(gson.toJson(entity));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
