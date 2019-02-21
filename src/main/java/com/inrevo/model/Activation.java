package com.inrevo.model;

//稼动
public class Activation {
    //使用中设备数   field1
    private Integer inUseNumber;
    //故障设备数    field2
    private Integer breakdownNunber;
    //闲置设备数    field3
    private Integer inIdleNumber;
    //到毫秒
    private Long time;
    //工厂 成都1 深圳2
    private Integer area;

    public Integer getInUseNumber() {
        return inUseNumber;
    }

    public void setInUseNumber(Integer inUseNumber) {
        this.inUseNumber = inUseNumber;
    }

    public Integer getBreakdownNunber() {
        return breakdownNunber;
    }

    public void setBreakdownNunber(Integer breakdownNunber) {
        this.breakdownNunber = breakdownNunber;
    }

    public Integer getInIdleNumber() {
        return inIdleNumber;
    }

    public void setInIdleNumber(Integer inIdleNumber) {
        this.inIdleNumber = inIdleNumber;
    }

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
}
