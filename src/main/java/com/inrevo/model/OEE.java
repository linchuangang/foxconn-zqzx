package com.inrevo.model;

//oee
public class OEE {
    //设备类型  tag1
    private String deviceType;
    //可用率   filed1
    private Double oee;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Double getOee() {
        return oee;
    }

    public void setOee(Double oee) {
        this.oee = oee;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
