package com.excalibur.znovel.bean;

import java.io.Serializable;

public class Message implements Serializable{

    private int id;
    private String message;
    private String sendTime;
    private String readTime;
    private int fromUid;
    private int toUid;
    private int type;
    private int isRead;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public int getFromUid() {
        return fromUid;
    }

    public void setFromUid(int fromUid) {
        this.fromUid = fromUid;
    }

    public int getToUid() {
        return toUid;
    }

    public void setToUid(int toUid) {
        this.toUid = toUid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", readTime='" + readTime + '\'' +
                ", fromUid=" + fromUid +
                ", toUid=" + toUid +
                ", type=" + type +
                ", isRead=" + isRead +
                ", status='" + status + '\'' +
                '}';
    }
}
