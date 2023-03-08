package com.bittlebittle.utils;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServiceInterface {

    // selectList
    List<Object> selectList(Object obj);

    // selectOne
    Object selectOne(Object obj);

    // insert
    int insert(Object obj);

    // update
    Object update(Object obj);

    // delete
    int delete(Object obj);

}
