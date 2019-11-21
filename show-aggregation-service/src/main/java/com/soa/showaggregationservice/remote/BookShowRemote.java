package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.BookInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name= "book-show-service",fallback = BookShowRemoteHystrix.class)
public interface BookShowRemote {
    @GetMapping("/v1/book-information-by-id/{id}")
    public BookInfo bookInfoShowById(@PathVariable(value = "id") String id);

    @GetMapping("/v1/book-information-by-title/{title}")
    public List<BookInfo> bookInfoShowByTitle(@PathVariable(value = "title") String title);
}
