package com.spring.bittlebittle.user.controller;


import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping(value = "api/users", produces = "application/json; charset=utf-8")
public class UserController {

    private Logger log = LogManager.getLogger("case3");

    @Autowired
    private UserService service;


    @PostMapping
    public void post(@ModelAttribute User user) {
        service.insert(user);
    }

    @GetMapping()
    public List<User> get(){
        log.debug("user 전체 조회");
        return service.selectList();
    }
    public Object update(User user) {
        return null;
    }

}
