package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Driver;

@Repository

public interface DriverRepo extends JpaRepository<Driver,Integer> {

   
    
}
