package com.soa.bookservice.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface IMongoPublicDao<T> {
    public void update(Query query, Update update, Class t);
}
