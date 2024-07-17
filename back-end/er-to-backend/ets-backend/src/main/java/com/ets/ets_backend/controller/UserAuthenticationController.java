package com.ets.ets_backend.controller;

import com.ets.ets_backend.security.JwtUtil;
import com.ets.ets_backend.model.Dashboard;
import com.ets.ets_backend.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api0/sign-in")
public class UserAuthenticationController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwt;

    @Autowired


    @GetMapping("/sign-in")
    public ResponseEntity<Dashboard> getUserDashboard(){
        Authentication authentication =
    }
}
