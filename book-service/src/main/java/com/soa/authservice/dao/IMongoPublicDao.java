package com.soa.authservice.dao;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public interface IMongoPublicDao<T> {
    public void update(Query query, Update update, Class t);
    public List<T> query(Query query,Class t);

}
