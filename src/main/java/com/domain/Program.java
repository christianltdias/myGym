package com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String nome;
    private User user;
    private Date creationDate;
    private Date expirationDate;

    private List<Serie> series = new ArrayList<>();

}