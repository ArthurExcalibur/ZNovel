package com.excalibur.znovel.util;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Base64;

public class FileUtil {

    public static String uploadFile(InputStream in, ServletContext context){
        String path = context.getRealPath("/file/girl.jpg");
        File f = new File("d:/temp","aaa.txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int n;

            while((n=in.read(b))!=-1){
                fos.write(b,0,n);
            }

            fos.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertTime2Path(){
        long time = System.currentTimeMillis();
        String string = String.valueOf(time);
        String encode = Base64.getEncoder().encodeToString(string.getBytes());
        return encode;
    }
}
