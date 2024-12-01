package com.orelease.etc.impl.controller;


import com.orelease.etc.impl.entity.Client;
import com.orelease.etc.impl.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api0/sign-up")
public class ClientRegistrationController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody Client user){
        service.createUser(user);
        // set the status of the response and build it without body.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
