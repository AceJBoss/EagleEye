package com.jboss.eagleeye.controller;

import com.jboss.eagleeye.model.Incident;
import com.jboss.eagleeye.model.User;
import com.jboss.eagleeye.service.IncidentService;
import com.jboss.eagleeye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IncidentService incidentService;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user){
        return userService.registerUser(user);
    }
    @PostMapping("/report-incident")
    public ResponseEntity<Incident>reportIncident(@RequestBody Incident incident){
        return new ResponseEntity(incidentService.reportIncident(incident), HttpStatus.OK);
    }
    @GetMapping("/view-incident/{id}")
    public List<Incident> incidentList(Long id){
        return incidentService.fetchIncidentsByMunicipal(id);
    }

}
