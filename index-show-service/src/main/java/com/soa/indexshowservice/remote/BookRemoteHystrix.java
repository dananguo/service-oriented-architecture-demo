package com.soa.indexshowservice.remote;

import com.soa.indexshowservice.pojo.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Component
public class BookRemoteHystrix implements BookRemote {

    @Override
    @GetMapping("/v1/Books/{count}")
    public List<BookInfo> QuerySomeBook(@PathVariable("count")int count) {
        return null;
    }
}
