package com.inrevo.model;

//工安
public class PulicSecurity {
    //到毫秒
    private Long time;
    //工厂 成都1 深圳2
    private Integer area;
    //隐患数   field1
    private Integer hiddenDangerNumber;
    //整改数   field2
    private Integer rectificationNumber;
    //整改率   field3
    private Double rectificationRate;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getHiddenDangerNumber() {
        return hiddenDangerNumber;
    }

    public void setHiddenDangerNumber(Integer hiddenDangerNumber) {
        this.hiddenDangerNumber = hiddenDangerNumber;
    }

    public Integer getRectificationNumber() {
        return rectificationNumber;
    }

    public void setRectificationNumber(Integer rectificationNumber) {
        this.rectificationNumber = rectificationNumber;
    }


    public Double getRectificationRate() {
        return rectificationRate;
    }

    public void setRectificationRate(Double rectificationRate) {
        this.rectificationRate = rectificationRate;
    }
}
