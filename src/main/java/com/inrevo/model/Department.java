package com.inrevo.model;
//部门
public class Department {
    //部门名称   tag1
    private String name;
    //人均产值   field1
    private String productionValue;
    //   field2
    private String upph;
    //耗材费   field3
    private String materialExpense;
    //折旧费   field4
    private String depreciationCost;
    //到毫秒
    private Long time;
    //工厂 成都1 深圳2
    private Integer area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductionValue() {
        return productionValue;
    }

    public void setProductionValue(String productionValue) {
        this.productionValue = productionValue;
    }

    public String getUpph() {
        return upph;
    }

    public void setUpph(String upph) {
        this.upph = upph;
    }

    public String getMaterialExpense() {
        return materialExpense;
    }

    public void setMaterialExpense(String materialExpense) {
        this.materialExpense = materialExpense;
    }

    public String getDepreciationCost() {
        return depreciationCost;
    }

    public void setDepreciationCost(String depreciationCost) {
        this.depreciationCost = depreciationCost;
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
