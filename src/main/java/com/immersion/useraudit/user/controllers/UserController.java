package com.immersion.useraudit.user.controllers;

import com.immersion.useraudit.user.domain.model.User;
import com.immersion.useraudit.user.exception.UserNotFoundException;
import com.immersion.useraudit.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try{
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (UserNotFoundException ex)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/viewUserDetails/{id}")
    public ResponseEntity<User> viewUserDetails(@PathVariable String id) {
        User user = userService.viewUserDetails(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("viewAllUsers")
    public  ResponseEntity<List<User>> viewAllUsers()
    {
        List<User> users= userService.viewAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
}

