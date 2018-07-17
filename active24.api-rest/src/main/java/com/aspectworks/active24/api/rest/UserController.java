package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserVO user) {
        System.out.println("Creating new user: " + user);
        userService.createUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        System.out.println("Deleting user with username: " + username);
        userService.deleteUser(username);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserVO> getAllUsers() {
        return userService.getAllUsers();
    }
}
