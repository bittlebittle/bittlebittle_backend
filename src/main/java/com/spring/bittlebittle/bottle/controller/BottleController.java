package com.spring.bittlebittle.bottle.controller;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/bottles")
public class BottleController {

    @Autowired
    private BottleService service;

    @GetMapping(value="/bottles")
    public String selectAll() {

        List<Bottle> selectAll = service.getAllBottles();

        return "allBottle";
    }




}
