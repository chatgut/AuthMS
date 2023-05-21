package com.example.authms.service;


import com.example.authms.exception.UserNotFoundException;
import com.example.authms.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);
    User getUserByNameAndPassword(String name, String password) throws UserNotFoundException;

}
