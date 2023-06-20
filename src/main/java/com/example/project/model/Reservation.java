package com.example.project.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int reservationNo;
    public Date checkInDate;
    public Date checkOutDate;

@ManyToOne
@JoinColumn(name = "spaceNo")
private Space space;

    public int getReservationNo() {
        return reservationNo;
    }
    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
}
