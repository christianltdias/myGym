package com.exercisetracker.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("weightItem")
public class WeightItem extends SerieItem {
    private static final long serialVersionUID = 1L;
    
    private Double weight;

    public WeightItem(){
        
    }

    public WeightItem(Integer id, Integer set, Exercise exercise, Serie serie, Double weight) {
        super(id, set, exercise, serie);
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

     

}