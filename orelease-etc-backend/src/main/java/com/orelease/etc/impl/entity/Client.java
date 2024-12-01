package com.orelease.etc.impl.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * User Entity class, representing users' structured data stored in database
 * */

// the name of this class will be a table in postgres,
// since 'user' is reserved in postgres we use the annotation below to give the table other name:
// @Table(name = "app_user") // Specify a different name here
// ot changing from user to client
@Data
@Table(name = "clients") // Specify a different name here
@Entity

public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 20)
    @Column(name = "uname", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(min=8)
    @Column(name="password", nullable = false)
    private String password;

//    @NotBlank
//    @Column(nullable = false)
//    byte[] salt;

//    @NotBlank
//    @Column(name="ticket", nullable = false)
//    private String ticket;

    @Column(nullable = false)
    private Boolean exited;
}
