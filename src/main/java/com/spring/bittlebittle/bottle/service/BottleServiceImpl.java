package com.spring.bittlebittle.bottle.service;


import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottleServiceImpl implements BottleService{

    @Autowired
    private BottleDao dao;

    @Override
    public List<Bottle> getAllBottles() {
        return dao.selectAll();
    }
}
