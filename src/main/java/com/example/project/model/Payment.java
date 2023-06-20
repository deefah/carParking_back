package com.example.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int feesID;
    public String feesAmount;
    public String feesDescr;

@ManyToOne
@JoinColumn(name = "reservationNo")
private Reservation reservation;


    public int getFeesID() {
        return feesID;
    }
    public void setFeesID(int feesID) {
        this.feesID = feesID;
    }
    public String getFeesAmount() {
        return feesAmount;
    }
    public void setFeesAmount(String feesAmount) {
        this.feesAmount = feesAmount;
    }
    public String getFeesDescr() {
        return feesDescr;
    }
    public void setFeesDescr(String feesDescr) {
        this.feesDescr = feesDescr;
    }


    
}
