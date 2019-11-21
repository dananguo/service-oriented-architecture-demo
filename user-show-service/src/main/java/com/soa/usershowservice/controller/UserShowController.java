package com.soa.usershowservice.controller;

import com.soa.usershowservice.pojo.PersonInfo;
import com.soa.usershowservice.remote.PersonRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableSwagger2Doc
@RestController
@RequestMapping("/v1")
public class UserShowController {
    @Autowired
    PersonRemote personRemote;
    @GetMapping("/user-show-by-id/{id}")
    public PersonInfo showUser(@PathVariable("id") String id){
        return personRemote.QueryPerson(id);
    }

    @GetMapping("/user-show-by-name/{name}")
    public List showUserByName(@PathVariable("name") String name){
        return personRemote.QueryPersonByName(name);
    }
}