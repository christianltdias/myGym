package com.domain;

import java.io.Serializable;

public class ExerciseItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private Exercise exercise;
    private Integer set;
    private Serie serie;
    
}