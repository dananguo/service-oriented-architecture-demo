package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryRemoteHystrix implements InventoryRemote {
    @GetMapping("/v1/inventory-by-user_id/{user_id}")
    public List<Book> QueryInventoryByUserId(@PathVariable("user_id") String id){
        return new ArrayList<>();
    }
}
