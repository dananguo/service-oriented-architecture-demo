package com.soa.bookservice.service.impl;

import com.soa.bookservice.dao.IMongoPublicDao;
import com.soa.bookservice.pojo.BookInfo;
import com.soa.bookservice.repository.BookInfoRepository;
import com.soa.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceimpl implements BookService {

    @Autowired
    private BookInfoRepository bookInfoRepository;
    @Autowired
    private IMongoPublicDao<BookInfo> publicDao;


    @Override
    public void save(BookInfo BookInfo) {
        bookInfoRepository.save(BookInfo);
    }

    @Override
    public BookInfo findById(String id) {
        return bookInfoRepository.findById(id).get();


    }

    @Override
    public List<BookInfo> findByBookName(String book_title) {

        if (book_title != null ) {
            Query query = new Query(Criteria.where("book_title").is(book_title));
            return publicDao.query(query,BookInfo.class);
        }
        else
            return null;


    }

    @Override
    public List<BookInfo> findAll() {
        return bookInfoRepository.findAll();
    }

    @Override
    public void updateTitleById(String book_id, String book_title) {
        Query query = new Query(Criteria.where("_id").is(book_id));
        Update update = Update.update("book_title",book_title);
        publicDao.update(query,update,BookInfo.class);
    }
    @Override
    public void deleteById(String book_id){
        bookInfoRepository.deleteById(book_id);
    }

    @Override
    public void deleteAll() {
        bookInfoRepository.deleteAll();
    }
}
