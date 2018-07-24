package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/users")
@Api(value = "/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "create user")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserVO user) {
        System.out.println("Creating new user: " + user);
        userService.createUser(user);
    }

    @ApiOperation(value = "delete user")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        System.out.println("Deleting user with username: " + username);
        userService.deleteUser(username);
    }

    @ApiOperation(value = "get all users", response = UserVO.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserVO> getAllUsers() {
        return userService.getAllUsers();
    }
}
