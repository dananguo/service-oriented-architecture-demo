package com.soa.indexshowservice.remote;

import com.soa.indexshowservice.pojo.BookInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BookRemoteHystrix implements BookRemote {
    @Override
    public List<BookInfo> QuerySomeBook(@RequestParam(value="count") int count){
        return null;
    }
}
