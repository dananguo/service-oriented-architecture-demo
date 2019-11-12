package com.soa.indexshowservice.controller;

import com.soa.indexshowservice.remote.BookRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: index-show-service
 * @description: controller for index show
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@RestController
public class IndexShowController {
    @Autowired
    BookRemote bookRemote;

    @GetMapping("/IndexShow/count={count}")
    public List showIndex(@PathVariable("count") int count){
        return bookRemote.QuerySomeBook(count);
    }
}