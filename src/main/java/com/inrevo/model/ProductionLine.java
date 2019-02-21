package com.inrevo.model;

//生产线
public class ProductionLine {
    //制程段  tag1
    private String manufactureProcedure;
    //总数量  filed1
    private Integer totalNumber;
    //在用数量  filed2
    private Integer inUseNUmber;
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

    public String getManufactureProcedure() {
        return manufactureProcedure;
    }

    public void setManufactureProcedure(String manufactureProcedure) {
        this.manufactureProcedure = manufactureProcedure;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getInUseNUmber() {
        return inUseNUmber;
    }

    public void setInUseNUmber(Integer inUseNUmber) {
        this.inUseNUmber = inUseNUmber;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
