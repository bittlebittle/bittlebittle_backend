package com.spring.bittlebittle.bottle.service;


import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;


public interface BottleService {

	List<Bottle> getBottles();
    
	Map<String, Object> getBottle(int bottleNo);

	Map<String, Object> getBottleByAdmin(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);


	Map<String, Object> editBottle(BottleInfo editBottle);

	List<Bottle> removeBottle(int bottleNo);

	List<Bottle> addBottle(BottleInfo bottle);




}
