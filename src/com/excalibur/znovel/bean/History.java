package com.excalibur.znovel.bean;

import java.io.Serializable;

public class History implements Serializable{


    private int id;
    private int uid;
    private int bid;
    private String firstReadTime;
    private String lastReadTime;
    private String totalReadTime;
    private String comment;
    private String status;
    private int zhangjie;
    private int zhangjieIndex;
    private String percent;

    private String name;
    private String author;
    private String desc;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getFirstReadTime() {
        return firstReadTime;
    }

    public void setFirstReadTime(String firstReadTime) {
        this.firstReadTime = firstReadTime;
    }

    public String getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(String lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public String getTotalReadTime() {
        return totalReadTime;
    }

    public void setTotalReadTime(String totalReadTime) {
        this.totalReadTime = totalReadTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getZhangjie() {
        return zhangjie;
    }

    public void setZhangjie(int zhangjie) {
        this.zhangjie = zhangjie;
    }

    public int getZhangjieIndex() {
        return zhangjieIndex;
    }

    public void setZhangjieIndex(int zhangjieIndex) {
        this.zhangjieIndex = zhangjieIndex;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", uid=" + uid +
                ", bid=" + bid +
                ", firstReadTime='" + firstReadTime + '\'' +
                ", lastReadTime='" + lastReadTime + '\'' +
                ", totalReadTime='" + totalReadTime + '\'' +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                ", zhangjie=" + zhangjie +
                ", zhangjieIndex=" + zhangjieIndex +
                ", percent='" + percent + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
