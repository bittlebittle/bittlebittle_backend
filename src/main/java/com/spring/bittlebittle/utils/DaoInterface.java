package com.spring.bittlebittle.utils;

import java.util.List;

public interface DaoInterface {

    // selectList
    List<Object> selectList(Object obj);

    // selectOne
    Object selectOne(Object obj);

    // insert
    int insert(Object obj);

    // update
    int update(Object obj);

    // delete
    int delete(Object obj);

}