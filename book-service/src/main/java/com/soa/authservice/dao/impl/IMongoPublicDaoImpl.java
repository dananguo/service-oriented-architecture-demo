package com.soa.authservice.dao.impl;

import com.soa.authservice.dao.IMongoPublicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyc
 * @date 2019/11/5 10:15
 */

@Repository
public class IMongoPublicDaoImpl<T> implements IMongoPublicDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void update(Query query, Update update, Class t) {

        mongoTemplate.updateMulti(query,update,t);
    }
    public List<T> query(Query query, Class t){
        return mongoTemplate.find(query,t);
    }
}
