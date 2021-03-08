package com.example.comparator.services;

import com.example.comparator.models.User;
import com.example.comparator.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public User saveUser(User user) {
        userRepo.save(user);
        return userRepo.findById(user.getId()).orElse(null);
    }

    @Transactional
    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }
}
