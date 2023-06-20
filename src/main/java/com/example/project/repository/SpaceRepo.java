package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Space;

@Repository
public interface SpaceRepo extends JpaRepository<Space,Integer> {
    
}
