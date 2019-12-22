package com.soa.inventoryservice.service;

import com.soa.inventoryservice.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {
    public Book findById(String id);

    public List<Book> findAll();

    public void delete(String id);

    public void save(Book book);

    public void deleteAll();

    public List<Book> findByUser_id(String user_id);
}
