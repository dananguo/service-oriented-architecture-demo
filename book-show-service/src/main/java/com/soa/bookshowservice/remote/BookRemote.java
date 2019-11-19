package com.soa.bookshowservice.remote;

import com.soa.bookshowservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "book-service",fallback = BookRemoteHystrix.class)
public interface BookRemote {
    //获取书籍信息
    @GetMapping("/Book")
    public BookInfo QueryBook(@RequestParam(value = "id") String id);

}
