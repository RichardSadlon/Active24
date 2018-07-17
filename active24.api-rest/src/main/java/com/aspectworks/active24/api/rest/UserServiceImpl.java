package com.aspectworks.active24.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import com.aspectworks.active24.api.rest.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<UserVO> users = new ArrayList<>();

    public void createUser(UserVO user) {
        users.add(user);
    }

    public void deleteUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                System.out.println("User " + username + " was deleted.");
                return;
            }
        }
    }

    public List<UserVO> getAllUsers() {
        return users;
    }

}
