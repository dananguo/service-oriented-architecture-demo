package com.soa.bookservice.service;

import com.soa.bookservice.pojo.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService {
    public void save(BookInfo BookInfo);

    public BookInfo findById(String book_id);

    public List<BookInfo> findAll();

    public void updateTitleById(String book_id,String book_title);

    public void deleteById(String book_id);

    public void deleteAll();
}
