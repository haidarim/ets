package com.ets.ets_backend.controller;


import com.ets.ets_backend.model.Client;
import com.ets.ets_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
// @RequestMapping(name = "/api0/project")
public class ProjectController {


    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "api0/project/{token}/{id}")
    public ResponseEntity<?> getProject(@PathVariable String token,
                                        @PathVariable String id){
        return ResponseEntity.ok("###::::###");
    }
}
