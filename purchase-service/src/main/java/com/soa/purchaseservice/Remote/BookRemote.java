package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name= "book-service",fallback = BookRemoteHystrix.class)
public interface BookRemote {
    //获取书籍信息
    @GetMapping("/v1/Book/{id}")
    public BookInfo QueryBook(@PathVariable("id") String id);

    //获取书籍信息
    @GetMapping("/v1/book-by-title/{title}")
    public List<BookInfo> QueryBookByTitle(@PathVariable(value = "title") String title);

}
