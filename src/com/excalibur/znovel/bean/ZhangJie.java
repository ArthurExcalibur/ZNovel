package com.excalibur.znovel.bean;

import java.io.Serializable;

public class ZhangJie implements Serializable {

    private String name;
    private int index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ZhangJie{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
