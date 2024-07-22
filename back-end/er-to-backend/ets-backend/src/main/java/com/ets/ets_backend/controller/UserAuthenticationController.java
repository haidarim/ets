package com.ets.ets_backend.controller;

import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.security.ETSUserDetails;
import com.ets.ets_backend.security.JwtUtil;
import com.ets.ets_backend.model.Project;
import com.ets.ets_backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api0/sign-in")
public class UserAuthenticationController {
    @Autowired
    private ClientService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


//    @PostMapping
//    public ResponseEntity<?> authenticateUser(@RequestBody Client user){
//        // authenticate the user, using authenticationManager
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        ETSUserDetails userDetails = (ETSUserDetails) authentication.getPrincipal();
//        try{
//            String jwtToken = this.jwtUtil.generateToken(userDetails.getUser());
//            Project dashboard = userService
//        }catch (IllegalAccessException e){
//            // TODO: LOGG THE EXCEPTION
//        }
//
//    }
}
