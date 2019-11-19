package com.soa.indexshowservice.remote;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class BookRemoteHystrix implements BookRemote {
    @Override
    public List QuerySomeBook(@RequestParam(value="count") int count){
        return null;
    }
}
