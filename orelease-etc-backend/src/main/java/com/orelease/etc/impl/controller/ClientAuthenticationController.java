package com.orelease.etc.impl.controller;

import com.orelease.etc.impl.entity.Client;
import com.orelease.etc.config.security.JwtUtil;
import com.orelease.etc.impl.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping(path = "api0/sign-in")
public class ClientAuthenticationController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<HashMap<String, Object>> authenticateUser(@RequestBody Client client){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Long id = clientService.getClientId(client.getUsername());
            final String jwtToken = jwtUtil.generateToken(id);
            HashMap<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("Projects", "No Project Found");
            response.put("id", id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
