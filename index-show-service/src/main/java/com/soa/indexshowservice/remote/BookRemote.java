package com.soa.indexshowservice.remote;


import com.soa.indexshowservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Primary
@FeignClient(name= "book-service")
public interface BookRemote {

    @GetMapping("/v1/Books/{count}")
    public List<BookInfo> QuerySomeBook(@PathVariable("count") int count);
}
