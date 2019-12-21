package com.soa.bookshowservice.remote;


import com.soa.bookshowservice.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Primary
@FeignClient(name= "inventory-service",fallback = InventoryRemoteHystrix.class)
public interface InventoryRemote {
    @GetMapping("/v1/Inventory/{id}")
    public Book QueryInventory(@PathVariable("id") String id);
}
