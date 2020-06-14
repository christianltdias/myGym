package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercise implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String name;
    private String description;

    private List<Category> categories = new ArrayList<>();
    
}