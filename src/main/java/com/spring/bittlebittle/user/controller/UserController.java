package com.spring.bittlebittle.user.controller;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.utils.ServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping(value = "api/users", produces = "application/json; charset=utf-8")
public class UserController {

    private Logger log = LogManager.getLogger("case3");

    @Autowired
    @Qualifier("userService")
    private ServiceInterface service;
    // service = new USerService();



    @PostMapping
    public void post(@ModelAttribute User user) {
        service.insert(user);
    }

    @GetMapping
    public List<Object> get(){
        log.debug("user 전체 조회");
        return service.selectList("");
    }

    public Object update(User user) {
        return null;
    }

}
