package com.soa.uploadservice.remote;


import com.soa.uploadservice.pojo.BookInfo;
import com.soa.uploadservice.pojo.Stand_Result;
import org.springframework.stereotype.Component;

@Component
public class BookRemoteHystrix implements BookRemote {
    @Override
    public String NewBook(BookInfo bookInfo) {
        return "调用Book基础服务失败";
    }

    @Override
    public Stand_Result Update(BookInfo bookInfo) {

        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return null;
    }

    @Override
    public Stand_Result DeleteBook(String id) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return null;
    }
}
