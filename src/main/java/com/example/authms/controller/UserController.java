package com.example.authms.controller;

import com.example.authms.config.JwtGeneratorInterface;
import com.example.authms.exception.UserNotFoundException;
import com.example.authms.service.UserService;
import com.example.authms.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtGeneratorInterface jwtGenerator;

    @Autowired
    public UserController(UserService userService, JwtGeneratorInterface jwtGenerator){
        this.userService=userService;
        this.jwtGenerator =jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try {
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        try {
            if (user.getUsername() == null || user.getPassword() == null){
                throw new UserNotFoundException("Username or password is Empty");
            }
            User userData = userService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            if (userData == null){
                throw new UserNotFoundException("Username or password is invalid");
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}


