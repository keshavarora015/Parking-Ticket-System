package com.squadstack.parkingticketsystem;

/**
 * Car Details Model
 * */
public class CarDetails {
    private String regNo;
    private Integer driverAge;

    public CarDetails(String regNo, Integer driverAge) {
        this.regNo = regNo;
        this.driverAge = driverAge;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }
}
