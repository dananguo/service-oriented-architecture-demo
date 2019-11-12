package com.soa.usershowservice.controller;

import com.soa.usershowservice.pojo.PersonInfo;
import com.soa.usershowservice.remote.PersonRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserShowController {
    @Autowired
    PersonRemote personRemote;
    @ResponseBody
    @GetMapping("/UserShow/id={id}")
    public PersonInfo showUser(@PathVariable("id") String id){
        return personRemote.QueryPerson(id);
    }
}