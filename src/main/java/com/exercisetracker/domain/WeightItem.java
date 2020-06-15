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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
        WeightItem other = (WeightItem) obj;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        return true;
    }

    

}