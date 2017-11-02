package com.excalibur.znovel.bean;

import java.io.Serializable;

public class Comment implements Serializable {

    private int id;
    private int type;
    private String comment;
    private int pinglunId;
    private int beipingId;
    private int huifuId;
    private String time;
    private int star;
    private String name;
    private String img;
    private String status;

    private int dianzanshu;
    private int pinglunshu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPinglunId() {
        return pinglunId;
    }

    public void setPinglunId(int pinglunId) {
        this.pinglunId = pinglunId;
    }

    public int getBeipingId() {
        return beipingId;
    }

    public void setBeipingId(int beipingId) {
        this.beipingId = beipingId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHuifuId() {
        return huifuId;
    }

    public void setHuifuId(int huifuId) {
        this.huifuId = huifuId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDianzanshu() {
        return dianzanshu;
    }

    public void setDianzanshu(int dianzanshu) {
        this.dianzanshu = dianzanshu;
    }

    public int getPinglunshu() {
        return pinglunshu;
    }

    public void setPinglunshu(int pinglunshu) {
        this.pinglunshu = pinglunshu;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                ", pinglunId=" + pinglunId +
                ", beipingId=" + beipingId +
                ", huifuId=" + huifuId +
                ", time='" + time + '\'' +
                ", star=" + star +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", status='" + status + '\'' +
                ", dianzanshu=" + dianzanshu +
                ", pinglunshu=" + pinglunshu +
                '}';
    }
}
