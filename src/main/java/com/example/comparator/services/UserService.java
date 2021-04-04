package com.example.comparator.services;

import com.example.comparator.models.User;
import com.example.comparator.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Сервис для работы с пользователями */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Сохранить пользователя
     * @param user пользователь
     * @return сохранённый пользователь или null
     */
    @Transactional
    public User saveUser(User user) {
        userRepo.save(user);
        return userRepo.findById(user.getId()).orElse(null);
    }

    /**
     * Найти пользователя по логину
     * @param login логин
     * @return пользователь или null
     */
    @Transactional
    public User findUserByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }
}
