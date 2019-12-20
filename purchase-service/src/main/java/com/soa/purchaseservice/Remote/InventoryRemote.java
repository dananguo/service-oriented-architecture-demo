package com.soa.purchaseservice.Remote;

import com.soa.purchaseservice.pojo.Book;
import com.soa.purchaseservice.pojo.Stand_Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name= "inventory-service",fallback = InventoryRemoteHystrix.class)
public interface InventoryRemote {
    //新建库存
    @PostMapping("/v1/Inventory")
    public String  NewInventory(@RequestBody Book book);

    //修改库存
    @PutMapping("/v1/Inventory")
    public Stand_Result Update(@RequestBody Book book);

    //删除库存
    @DeleteMapping("/v1/Inventory/{id}")
    public Stand_Result DeleteInventory(@PathVariable("id") String id);

    //获取剩余库存
    @GetMapping("/v1/Inventory/{id}")
    public Book QueryInventory(@PathVariable("id") String id);
}
