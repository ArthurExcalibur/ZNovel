package com.excalibur.znovel.data;

import java.io.Serializable;

public class ShuChengEntity implements Serializable {

    private String hot;
    private String xinshu;
    private String guess;
    private String gelei;

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getXinshu() {
        return xinshu;
    }

    public void setXinshu(String xinshu) {
        this.xinshu = xinshu;
    }

    public String getGelei() {
        return gelei;
    }

    public void setGelei(String gelei) {
        this.gelei = gelei;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
