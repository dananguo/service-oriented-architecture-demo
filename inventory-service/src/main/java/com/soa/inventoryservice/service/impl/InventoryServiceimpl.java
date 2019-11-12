package com.soa.inventoryservice.service.impl;

import com.soa.inventoryservice.pojo.Book;
import com.soa.inventoryservice.repository.BookRepository;
import com.soa.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceimpl implements InventoryService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findById(String id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(String id)  {
        bookRepository.deleteById(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }
}
