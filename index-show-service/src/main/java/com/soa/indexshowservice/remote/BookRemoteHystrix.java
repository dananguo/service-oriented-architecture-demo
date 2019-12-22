package com.soa.indexshowservice.remote;

import com.soa.indexshowservice.pojo.BookInfo;

import java.util.List;

public class BookRemoteHystrix implements BookRemote {

    @Override
    public List<BookInfo> QuerySomeBook(int count) {
        return null;
    }
}
