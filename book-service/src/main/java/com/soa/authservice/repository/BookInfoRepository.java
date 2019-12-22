package com.soa.authservice.repository;

import com.soa.authservice.pojo.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends MongoRepository<BookInfo, String> {

}
