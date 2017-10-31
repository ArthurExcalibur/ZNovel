package com.excalibur.znovel.data;

import java.io.Serializable;

public class BaseEntity implements Serializable{

    private long shijian;
    private boolean status;
    private String error_info;
    private String data;

    public void setShijian(long shijian) {
        this.shijian = shijian;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public void setData(String data) {
        this.data = data;
    }
}
