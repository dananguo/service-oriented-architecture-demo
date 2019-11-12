package com.soa.indexshowservice.remote;


import com.soa.indexshowservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name= "spring-cloud-Book")
public interface BookRemote {

    @GetMapping("Books")
    public List QuerySomeBook(@RequestParam(value="count") int count);
}
