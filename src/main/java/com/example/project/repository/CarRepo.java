package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Car;

@Repository

public interface CarRepo extends JpaRepository<Car,Integer>{
    
}
