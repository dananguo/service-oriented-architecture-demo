package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Primary
@FeignClient(name= "inventory-service",fallback = InventoryRemoteHystrix.class)
public interface InventoryRemote {

    @GetMapping("/v1/inventory-by-user_id/{user_id}")
    public List<Book> QueryInventoryByUserId(@PathVariable("user_id") String id);
}
