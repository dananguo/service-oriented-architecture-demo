package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.BookInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/11/21
 **/
@Component
public class BookShowRemoteHystrix implements BookShowRemote{

    @Override
    public BookInfo bookInfoShowById(@PathVariable(value = "id") String id)
    {
        return null;
    }


    @Override
    public List<BookInfo> bookInfoShowByTitle(@PathVariable(value = "title") String title)
    {
        return null;
    }
}