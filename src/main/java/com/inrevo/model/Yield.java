package com.inrevo.model;

//良率
public class Yield {
    //机种  tag1
    private String partName;
    //夹位  tag2
    private String fixture;
    //计划良率  filed1
    private Double planValue;
    //实际良率   filed2
    private Double actualValue;
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

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFixture() {
        return fixture;
    }

    public void setFixture(String fixture) {
        this.fixture = fixture;
    }

    public Double getPlanValue() {
        return planValue;
    }

    public void setPlanValue(Double planValue) {
        this.planValue = planValue;
    }

    public Double getActualValue() {
        return actualValue;
    }

    public void setActualValue(Double actualValue) {
        this.actualValue = actualValue;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
