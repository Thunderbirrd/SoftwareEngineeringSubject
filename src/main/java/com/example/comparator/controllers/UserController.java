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

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String userData) throws JSONException{
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
