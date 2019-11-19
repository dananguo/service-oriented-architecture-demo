package com.soa.showaggregationservice.remote;


import com.soa.showaggregationservice.pojo.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "inventory-service",fallback = InventoryRemoteHystrix.class)
public interface InventoryRemote {
    @GetMapping("/Inventory")
    public Book QueryInventory(@RequestParam(value="id") String id);
}
