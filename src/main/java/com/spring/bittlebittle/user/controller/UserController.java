package com.spring.bittlebittle.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;

@Controller
public class UserController {

    private Logger log = LogManager.getLogger("case3");

    @Autowired
    private UserService service;

    @PostMapping(value = "api/users")
    public void post(@ModelAttribute User user) {
        service.insert(user);
    }

    @GetMapping(value = "api/users")
    public void get2(){
        System.out.println(112111);
        log.debug("dddd");
    }

    @RequestMapping(value = "start")
    public String get4() {
        System.out.println(222);
        return "start";
    }
}
