package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.UserRegistration;

@Repository
public interface UserRepo extends JpaRepository<UserRegistration,Integer> {
    
}
