package com.exercisetracker.repositories;


import com.exercisetracker.domain.Program;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{
    
}