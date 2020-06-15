package com.exercisetracker.repositories;


import com.exercisetracker.domain.Muscle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Integer>{
    
}