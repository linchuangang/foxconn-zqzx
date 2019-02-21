package com.inrevo.model;

//库存
public class Repertory {
    //半成品1 ,成品 2   tag1
    private Integer quality;
    //库存天数   filed1
    private Double day;
    //到毫秒
    private Long time;
    //工厂 成都1 深圳2
    private Integer area;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
