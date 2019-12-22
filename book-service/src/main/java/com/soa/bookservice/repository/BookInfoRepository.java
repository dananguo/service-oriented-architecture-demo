package com.soa.bookservice.repository;

import com.soa.bookservice.pojo.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends MongoRepository<BookInfo, String> {

}
