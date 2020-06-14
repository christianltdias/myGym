package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String name;
    private Program program;
    private boolean active;
    private List<ExerciseItem> itens = new ArrayList<>();

}