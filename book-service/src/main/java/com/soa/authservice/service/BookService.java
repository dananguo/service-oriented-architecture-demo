package com.soa.authservice.service;

import com.soa.authservice.pojo.BookInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public void save(BookInfo BookInfo);

    public BookInfo findById(String book_id);

    public List<BookInfo> findByBookName(String book_title);

    public List<BookInfo> findAll();

    public void updateTitleById(String book_id,String book_title);

    public void deleteById(String book_id);

    public void deleteAll();
}
