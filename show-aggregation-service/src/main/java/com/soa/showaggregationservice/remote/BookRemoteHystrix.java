package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class BookRemoteHystrix implements BookRemote{
    @Override
    public BookInfo QueryBook(@RequestParam(value = "id") String id){
        return null;
    }
}
