package com.ets.ets_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 * User Entity class, representing users' structured data stored in database
 * */

// the name of this class will be a table in postgres,
// since 'user' is reserved in postgres we use the annotation below to give the table other name:
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;


}
