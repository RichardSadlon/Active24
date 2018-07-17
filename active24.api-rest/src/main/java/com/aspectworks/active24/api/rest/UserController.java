package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserVO user){
        System.out.println("Creating new user: " + user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public void deleteUser(@PathVariable("username") String username){
        System.out.println("Deleting user with username: " + username);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserVO> getAllUsers(){
        List<UserVO> users = new ArrayList<>();
        UserVO userVO = new UserVO();
        userVO.setUsername("user1");
        userVO.setFirstName("Tomas");
        userVO.setSurname("Blabol");
        users.add(userVO);
        userVO = new UserVO();
        userVO.setUsername("user2");
        userVO.setFirstName("Martin");
        userVO.setSurname("Blabolek");
        users.add(userVO);
        return users;
    }
}
