package com.soa.usershowservice.controller;

import com.soa.usershowservice.pojo.PersonInfo;
import com.soa.usershowservice.remote.PersonRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@EnableSwagger2Doc
@RestController
public class UserShowController {
    @Autowired
    PersonRemote personRemote;
    @GetMapping("/UserShow")
    public String showUser(@RequestParam("id") String id){
        return personRemote.QueryPerson(id).toString();
    }
}