package com.example.authms.config;

import com.example.authms.user.User;

import java.util.Map;

public interface JwtGeneratorInterface {

    Map<String, String> generateToken(User user);

}
