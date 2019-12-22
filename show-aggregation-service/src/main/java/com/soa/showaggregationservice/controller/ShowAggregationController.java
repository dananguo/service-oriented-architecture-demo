package com.soa.showaggregationservice.controller;

import com.soa.showaggregationservice.pojo.Aggregation;
import com.soa.showaggregationservice.pojo.Book;
import com.soa.showaggregationservice.pojo.BookInfo;
import com.soa.showaggregationservice.pojo.PersonInfo;
import com.soa.showaggregationservice.remote.*;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: show-aggregation-service
 * @description: controller for show aggregation
 * @author: Yu Liu
 * @create: 2019/11/11
 **/
@EnableSwagger2Doc
@RestController
@RequestMapping("/v1")
public class ShowAggregationController {
    @Autowired
    BookShowRemote bookShowRemote;

    @Autowired
    UserShowRemote userShowRemote;

    @Autowired
    InventoryRemote inventoryRemote;

    @Autowired
    HttpServletRequest request;

//    @GetMapping("/ShowAggregation")
//    public String aggregationShow(@RequestParam("id") String id){
//       Aggregation aggregation=new Aggregation();
//       BookInfo bookInfo=new BookInfo();
//       bookInfo=bookRemote.QueryBook(id);
//       aggregation.setBookInfo(bookInfo);
//       Book book=new Book();
//       book=inventoryRemote.QueryInventory(id);
//       aggregation.setBook(book);
//       String user_id=book.getBook_id();
//       PersonInfo person=new PersonInfo();
//       person=personRemote.QueryPerson(user_id);
//       aggregation.setPersonInfo(person);
//       return aggregation.toString();
//    }

    @GetMapping("/aggregation-by-id")
    public Aggregation show(){
        String id=request.getHeader("uid");
        Aggregation aggregation=new Aggregation();
        BookInfo book=bookShowRemote.bookInfoShowById(id);
        aggregation.setBook_title(book.getBook_title());

        Book inventory=inventoryRemote.QueryInventory(id);
        PersonInfo user=userShowRemote.showUser(inventory.getUser_id());
        aggregation.setUser_name(user.getName());
        aggregation.setBook_price(inventory.getBook_price());
        aggregation.setInventory_quantity(inventory.getInventory_quantity());

        return aggregation;
    }


}