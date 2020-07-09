package com.exercisetracker.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("timeItem")
public class TimeItem extends SerieItem{
    private static final long serialVersionUID = 1L;

    private Double time;

    public TimeItem(){

    }

    public TimeItem(Integer id, Integer set, Exercise exercise, Serie serie, Double time) {
        super(id, set, exercise, serie);
        this.time = time;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
    
    
}