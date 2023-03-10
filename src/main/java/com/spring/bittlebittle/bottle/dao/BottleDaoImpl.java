package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BottleDaoImpl implements BottleDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Bottle> selectAll() {
        return sqlSession.selectList("bottleMapper.selectAll");
    }
}