package com.soa.bookservice.dao.impl;

import com.soa.bookservice.dao.IMongoPublicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
}
