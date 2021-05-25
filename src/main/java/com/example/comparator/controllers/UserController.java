package com.example.comparator.controllers;

import com.example.comparator.models.User;
import com.example.comparator.services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/** Контроллер для работы с пользователями */
@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    /** Регистрация пользователя
     * @param userData данные пользователя
     * @return пользователь
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody String userData) throws JSONException {
        User user = new User();
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        String password = data.getString("password");
        if(userService.findUserByLogin(login) == null) {
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            return userService.saveUser(user);
        }
        return null;
    }

    /** Логин
     * @param userData данные пользователя
     * @return id пользователя или сообщение об ошибке
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String userData) throws JSONException{
        System.err.println(userData);
        JSONObject data = new JSONObject(userData);
        String login = data.getString("login");
        String password = data.getString("password");
        User user = userService.findUserByLogin(login);
        if(user == null){
            return "Wrong login";
        }
        else if(passwordEncoder.matches(password, user.getPassword())){
            return user.getId().toString();
        }else {
            return "Wrong password";
        }
    }
}
