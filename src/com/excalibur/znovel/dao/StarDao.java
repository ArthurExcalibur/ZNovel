package com.excalibur.znovel.dao;

public interface StarDao {

    boolean star(int zanId,int beizanId,int type);

    int getStarNumber(int id,int type);
}
