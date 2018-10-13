package ru.stacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.stacy.model.User;
import ru.stacy.service.UserService;

import java.util.Map;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // GET - для получения информации
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/users")
    public String setupForm(Map<String, Object> map) {
        User user = new User();
        map.put("user", user);
        map.put("listUsers", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/user.do", method = RequestMethod.POST)
    public String doActions(@ModelAttribute User user, @RequestParam String action, Map<String, Object> map) {
        User userResult = new User();

        switch (action.toLowerCase()) {
            case "add user" : userService.addUser(user);
                              userResult = user;
                              break;
            case "update user" : userService.updateUser(user);
                                 userResult = user;
                                 break;
            case "remove user" : userService.removeUser(user.getId());
                                 userResult = new User();
                                 break;
            case "search user" : User searchedUser = userService.getUserById(user.getId());
                                 userResult = searchedUser != null ? searchedUser : new User();
                                 break;
        }

        map.put("user", userResult);
        map.put("listUsers", userService.listUsers());

        return "users";
    }
}
