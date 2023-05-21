package com.example.authms.repository;

import com.example.authms.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameAndPassword(String username, String Password);

}
