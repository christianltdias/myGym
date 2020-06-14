package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Muscle implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String name;
    private List<Category> category = new ArrayList<>();
    private List<Exercise> exercises = new ArrayList<>();
    
}