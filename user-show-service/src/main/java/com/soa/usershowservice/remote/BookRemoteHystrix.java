package com.soa.usershowservice.remote;
import com.soa.usershowservice.pojo.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class BookRemoteHystrix implements BookRemote{


    @Override
    public BookInfo QueryBook(@RequestParam(value = "id") String id){
        return null;
    }



}
