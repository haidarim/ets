package com.ets.ets_backend.model;

import jakarta.persistence.*;
import lombok.Data;


/**
 * User Entity class, representing users' structured data stored in database
 * */

// the name of this class will be a table in postgres,
// since 'user' is reserved in postgres we use the annotation below to give the table other name:
// @Table(name = "app_user") // Specify a different name here
// ot changing from user to client

@Table(name = "clients") // Specify a different name here
@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
}
