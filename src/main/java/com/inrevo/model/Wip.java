package com.inrevo.model;
//wip
public class Wip {
    //机种  tag1
    private String partName;
    //制程段  tag2
    private String manufactureProcedure;
    //计划值   filed1
    private Integer planValue;
    //实际值   filed2
    private Integer actualValue;
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

    public String getManufactureProcedure() {
        return manufactureProcedure;
    }

    public void setManufactureProcedure(String manufactureProcedure) {
        this.manufactureProcedure = manufactureProcedure;
    }

    public Integer getPlanValue() {
        return planValue;
    }

    public void setPlanValue(Integer planValue) {
        this.planValue = planValue;
    }

    public Integer getActualValue() {
        return actualValue;
    }

    public void setActualValue(Integer actualValue) {
        this.actualValue = actualValue;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
