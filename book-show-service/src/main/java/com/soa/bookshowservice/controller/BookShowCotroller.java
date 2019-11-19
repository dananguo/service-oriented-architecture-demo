package com.soa.bookshowservice.controller;


import com.soa.bookshowservice.remote.BookRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: finalProject
 * @description: controller for book show  service
 * @author: Yu Liu
 * @create: 2019/11/14
 **/
@EnableSwagger2Doc
@RestController
public class BookShowCotroller {

        @Autowired
        BookRemote bookRemote;

        //@ResponseBody
        @GetMapping("/BookShow")
        public String showBook(@RequestParam String id)
        {

            return bookRemote.QueryBook(id).toString();
        }




}
