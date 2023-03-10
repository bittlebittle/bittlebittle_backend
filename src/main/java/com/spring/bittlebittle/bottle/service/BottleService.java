package com.spring.bittlebittle.bottle.service;


import com.spring.bittlebittle.bottle.vo.Bottle;

import java.util.List;

public interface BottleService {

	List<Bottle> getBottles();
    
	Bottle getBottle(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);

	Bottle addBottle(Bottle newBottle);

	Bottle updateBottle(Bottle updateBottle);


}
