package com.orelease.etc.impl.controller;


import com.orelease.etc.impl.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
