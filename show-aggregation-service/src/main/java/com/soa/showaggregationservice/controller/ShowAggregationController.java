package com.soa.showaggregationservice.controller;

import com.soa.showaggregationservice.ShowAggregation;
import com.soa.showaggregationservice.pojo.Aggregation;
import com.soa.showaggregationservice.pojo.Book;
import com.soa.showaggregationservice.pojo.BookInfo;
import com.soa.showaggregationservice.pojo.PersonInfo;
import com.soa.showaggregationservice.remote.BookRemote;
import com.soa.showaggregationservice.remote.InventoryRemote;
import com.soa.showaggregationservice.remote.PersonRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: show-aggregation-service
 * @description: controller for show aggregation
 * @author: Yu Liu
 * @create: 2019/11/11
 **/
@RestController
public class ShowAggregationController {
    @Autowired
    BookRemote bookRemote;

    @Autowired
    PersonRemote personRemote;

    @Autowired
    InventoryRemote inventoryRemote;

    @GetMapping("/ShowAggregation/id={id}")
    public Aggregation aggregationShow(@PathVariable("id") String id){
       Aggregation aggregation=new Aggregation();
       BookInfo bookInfo=new BookInfo();
       bookInfo=bookRemote.QueryBook(id);
       aggregation.setBookInfo(bookInfo);
       Book book=new Book();
       book=inventoryRemote.QueryInventory(id);
       aggregation.setBook(book);
        String user_id=book.getBook_id();
        PersonInfo person=new PersonInfo();
        person=personRemote.QueryPerson(user_id);
        aggregation.setPersonInfo(person);
        return aggregation;


    }



}