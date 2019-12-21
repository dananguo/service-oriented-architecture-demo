package com.soa.bookshowservice.remote;

import com.soa.bookshowservice.pojo.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class InventoryRemoteHystrix implements InventoryRemote {
    @Override
    public Book QueryInventory(@RequestParam(value="id") String id){
        return null;
    }
}
