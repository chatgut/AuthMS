package com.example.authms.service;


import com.example.authms.exception.UserNotFoundException;
import com.example.authms.repository.UserRepository;
import com.example.authms.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password)throws UserNotFoundException {
        User user = userRepository.findByUsername(name);
        if (user == null){
            throw new UserNotFoundException("Invalid id or Password");
        }
        return user;
    }
}
