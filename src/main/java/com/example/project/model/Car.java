package com.example.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    public int plateNo;
    public String carType;
    public String CarNumber;

    
    public String getCarNumber() {
        return CarNumber;
    }
    public void setCarNumber(String carNumber) {
        CarNumber = carNumber;
    }
    public int getPlateNo() {
        return plateNo;
    }
    public void setPlateNo(int plateNo) {
        this.plateNo = plateNo;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }

    
}
