package ru.elimental.lunchvote.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.elimental.lunchvote.model.User;
import ru.elimental.lunchvote.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = UserController.REST_URL)
public class UserController {

    public static final String REST_URL = "/v1/users";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@Valid @RequestBody User user) {
        userService.createUser(user);
    }

}
