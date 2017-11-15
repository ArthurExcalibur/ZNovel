package com.excalibur.znovel.dao;

public interface UserDao {

    boolean register(String name,String pass,String img,String desc,String sex,String resID);
    boolean updatePass(int id,String newPass);

    boolean checkForUser(String name);
    boolean checkForPass(int id,String pass);
    boolean checkForResID(int id,String resID);

    String updateResID(int id,String resID);
}
