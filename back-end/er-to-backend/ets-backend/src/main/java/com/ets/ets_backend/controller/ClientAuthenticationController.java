package com.ets.ets_backend.controller;

import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.model.Project;
import com.ets.ets_backend.security.ETSUserDetails;
import com.ets.ets_backend.security.JwtUtil;
import com.ets.ets_backend.service.ClientService;
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
    private ClientService userService;

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

            final String jwtToken = jwtUtil.generateToken(client);
            HashMap<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("Projects", "No Project Found");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
