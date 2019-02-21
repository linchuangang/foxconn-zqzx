package com.inrevo.model;

//排产
public class Plan {
    //机种  tag1
    private String partName;
    //计划值  filed1
    private Integer planValue;
    //实际值   filed2
    private Integer actualValue;
    //工厂 成都1 深圳2
    private Integer area;
    //到毫秒
    private Long time;

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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
