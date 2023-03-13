package com.spring.bittlebittle.bottle.service;


import com.spring.bittlebittle.bottle.vo.Bottle;

import java.util.List;

public interface BottleService {

	List<Bottle> getBottles();
    
	Bottle getBottle(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);

	int addBottle(Bottle newBottle);

	Bottle editBottle(Bottle updateBottle);

	List<Bottle> removeBottle(int bottleNo);



}
