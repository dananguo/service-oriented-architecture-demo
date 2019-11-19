package com.soa.showaggregationservice.remote;


import com.soa.showaggregationservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name= "book-service",fallback = BookRemoteHystrix.class)
public interface BookRemote {

    //获取书籍信息
    @GetMapping("/Book")
    public BookInfo QueryBook(@RequestParam(value = "id") String id);
}
