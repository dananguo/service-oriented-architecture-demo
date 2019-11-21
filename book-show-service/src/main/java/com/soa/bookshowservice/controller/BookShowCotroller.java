package com.soa.bookshowservice.controller;


import com.soa.bookshowservice.pojo.BookInfo;
import com.soa.bookshowservice.remote.BookRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: finalProject
 * @description: controller for book show  service
 * @author: Yu Liu
 * @create: 2019/11/14
 **/
@EnableSwagger2Doc
@RestController
@RequestMapping("/v1")
public class BookShowCotroller {

        @Autowired
        BookRemote bookRemote;


        @GetMapping("/book-information-by-id/{id}")
        public BookInfo bookInfoShowById(@PathVariable(value = "id") String id)
        {
                return bookRemote.QueryBook(id);
        }


        @GetMapping("/book-information-by-title/{title}")
        public List<BookInfo> bookInfoShowByTitle(@PathVariable(value = "title") String title)
        {
                return bookRemote.QueryBookByTitle(title);
        }




}
