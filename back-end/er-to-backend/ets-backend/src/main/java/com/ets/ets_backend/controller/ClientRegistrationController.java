package com.ets.ets_backend.controller;


import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api0/sign-up")
public class UserRegistrationController {

    @Autowired
    private ClientService service;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody Client user){
        service.createUser(user);
        // set the status of the response and build it without body.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
