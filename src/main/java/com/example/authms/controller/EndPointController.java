package com.example.authms.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/endpoint")
public class EndPointController {

    @GetMapping("/unrestricted")
    public ResponseEntity<?> getMesssage(){
        return new ResponseEntity<>("This is a normal message", HttpStatus.OK);
    }

    @GetMapping("/restricted")
    public ResponseEntity<?> getRestrictedMesssage(){
        return new ResponseEntity<>("This is a restricted message", HttpStatus.OK);
    }
}
