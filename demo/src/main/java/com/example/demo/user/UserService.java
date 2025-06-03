package com.example.demo.user;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

/**
 * Service layer is where all the business logic lies
 */
@Service
@RequiredArgsConstructor
public class UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepo;

    public List<User> getAllUsers(){
        logger.info("getting users from DB");
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepo.save(user);
        logger.info("user with id:{} saved successfully", user.getId());
        return savedUser;
    }

    public User loadUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
}