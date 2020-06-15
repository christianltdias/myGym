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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeItem other = (TimeItem) obj;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    
    
}