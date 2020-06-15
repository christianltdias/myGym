package com.exercisetracker.repositories;


import com.exercisetracker.domain.SerieItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieItemRepository extends JpaRepository<SerieItem, Integer>{
    
}