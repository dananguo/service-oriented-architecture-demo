package com.soa.serviceproducer.controller;

import com.soa.serviceproducer.pojo.addParams;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first messge";
    }
    @PostMapping("/add")
    public int add(@RequestBody addParams params){
        return params.getLeft() + params.getRight();
    }
}