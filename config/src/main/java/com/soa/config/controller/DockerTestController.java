package com.soa.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerTestController
{
    @GetMapping("/hello")
    public String index() {
        return "hello, this is first messge";
    }
}
