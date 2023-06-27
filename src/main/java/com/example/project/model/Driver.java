package com.example.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int driverID;
    public String firstName;
    public String lastName;
    public int mobileNo;


    @ManyToOne
    @JoinColumn(name = "plateNo")
    private Car car;

    // public int getDriverID() {
    //     return driverID;
    // }
    // public void setDriverID(int driverID) {
    //     this.driverID = driverID;
    // }
    // public String getFirstName() {
    //     return firstName;
    // }
    // public void setFirstName(String firstName) {
    //     this.firstName = firstName;
    // }
    // public String getLastName() {
    //     return lastName;
    // }
    // public void setLastName(String lastName) {
    //     this.lastName = lastName;
    // }
    // public int getMobileNo() {
    //     return mobileNo;
    // }
    // public void setMobileNo(int mobileNo) {
    //     this.mobileNo = mobileNo;
    // }
    
}
