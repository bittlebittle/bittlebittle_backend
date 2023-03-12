package com.spring.bittlebittle.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;



@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private Logger log = LogManager.getLogger("case3");

    @Autowired
    private UserService userService;
    
    
  

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    if (userService.checkUserExists(user.getUserName())) {
    response.put("status", "error");
    response.put("message", "User already exists");
    } else {
    userService.insertUser(user);
    response.put("status", "success");
    response.put("message", "User created successfully");
    }
    return response;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getAllUsers(User user) {
    List<User> users = userService.getAllUsers(user);
    return users;
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> getUserById(@PathVariable String userId) {
    Map<String, Object> response = new HashMap<>();
    User user = userService.getUserById(userId);
    if (user == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    response.put("status", "success");
    response.put("user", user);
    }
    return response;
    }

    @PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateUser(@PathVariable String userId, @RequestBody User user) {
    Map<String, Object> response = new HashMap<>();
    User existingUser = userService.getUserById(userId);
    if (existingUser == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    user.setUserId(userId);
    userService.updateUser(user);
    response.put("status", "success");
    response.put("message", "User updated successfully");
    }
    return response;
    }

    @DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable String userId) {
    Map<String, Object> response = new HashMap<>();
    User existingUser = userService.getUserById(userId);
    if (existingUser == null) {
    response.put("status", "error");
    response.put("message", "User not found");
    } else {
    userService.deleteUser(userId);
    response.put("status", "success");
    response.put("message", "User deleted successfully");
    }
    return response;
    }


}
