package com.soa.bookshowservice.controller;

import com.soa.bookshowservice.pojo.BookInfo;
import com.soa.bookshowservice.remote.BookRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: book-show-service
 * @description: controller for book show service
 * @author: Yu Liu
 * @create: 2019/11/11
 **/
@RestController
//@RequestMapping("/BookShow")
public class BookShowController {
    @Autowired
    BookRemote bookRemote;

    @ResponseBody
    @GetMapping("/BookShow/id={id}")
    public BookInfo showBook(@PathVariable("id") String id)
    {

       return bookRemote.QueryBook(id);
    }



}