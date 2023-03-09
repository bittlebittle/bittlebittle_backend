package com.spring.bittlebittle.bottle.service;

import java.util.List;

import com.spring.bittlebittle.bottle.vo.Bottle;

public interface BottleService {

	Bottle getBottle(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);

}
