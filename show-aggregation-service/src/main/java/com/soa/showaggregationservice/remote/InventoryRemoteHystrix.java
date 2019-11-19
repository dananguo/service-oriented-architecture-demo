package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class InventoryRemoteHystrix implements InventoryRemote {
    @Override
    public Book QueryInventory(@RequestParam(value="id") String id){
        return null;
    }
}
