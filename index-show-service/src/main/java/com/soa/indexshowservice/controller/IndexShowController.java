package com.soa.indexshowservice.controller;

import com.soa.indexshowservice.remote.BookRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: index-show-service
 * @description: controller for index show
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@EnableSwagger2Doc
@RestController
@RequestMapping("/v1")
public class IndexShowController {
    @Autowired
    BookRemote bookRemote;

    @GetMapping("/Index/{count}")
    public List showIndex(@PathVariable(value = "count") int count){
        return bookRemote.QuerySomeBook(count);
    }
}