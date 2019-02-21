package com.inrevo.model;

//出勤
public class Person {
    //部门  tag1
    private String department;
    //类型：派遣工0,正式工1，师级2   tag2
    private Integer type;
    //在职人数  filed1
    private Integer isOnJobNumber;
    //出勤人数  filed2
    private Integer outForWorkNumber;
    //到毫秒
    private Long time;
    //工厂 成都1 深圳2
    private Integer area;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
