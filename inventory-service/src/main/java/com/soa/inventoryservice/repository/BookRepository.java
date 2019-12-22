package com.soa.inventoryservice.repository;

import com.soa.inventoryservice.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query(value="select book from Book book where book.user_id= ?1")
    List<Book> findByUser_id(String user_id);
}

