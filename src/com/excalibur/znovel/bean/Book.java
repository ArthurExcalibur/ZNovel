package com.excalibur.znovel.bean;

import java.io.Serializable;

public class Book implements Serializable{

    private int id;
    private String name;
    private String url;
    private String img;
    private String description;
    private int status;
    private String type;
    private String author;
    private int textNumber;
    private int readNumber;
    private int lastUpdate;
    private String download;
    private String indexUrl;
    private int todayUpdate;
    private String tags;
    private String newZhangJie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTextNumber() {
        return textNumber;
    }

    public void setTextNumber(int textNumber) {
        this.textNumber = textNumber;
    }

    public int getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(int readNumber) {
        this.readNumber = readNumber;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public int getTodayUpdate() {
        return todayUpdate;
    }

    public void setTodayUpdate(int todayUpdate) {
        this.todayUpdate = todayUpdate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNewZhangJie() {
        return newZhangJie;
    }

    public void setNewZhangJie(String newZhangJie) {
        this.newZhangJie = newZhangJie;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", textNumber=" + textNumber +
                ", readNumber=" + readNumber +
                ", lastUpdate=" + lastUpdate +
                ", download='" + download + '\'' +
                ", indexUrl='" + indexUrl + '\'' +
                ", todayUpdate=" + todayUpdate +
                ", tags='" + tags + '\'' +
                ", newZhangJie='" + newZhangJie + '\'' +
                '}';
    }
}
