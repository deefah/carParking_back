package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Integer>{
    
}
