package com.soa.inventoryservice.controller;

import com.soa.inventoryservice.pojo.Book;
import com.soa.inventoryservice.pojo.Stand_Result;
import com.soa.inventoryservice.service.InventoryService;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@EnableSwagger2Doc
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    //新建库存
    @PostMapping("/Inventory")
    public Stand_Result NewInventory(@RequestBody Book book) {
        inventoryService.save(book);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //修改库存
    @PutMapping("/Inventory")
    public Stand_Result ModifyInventory(@RequestParam(value = "id") String id) {
        /*没有做，先放着*/
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }

    //删除库存
    @DeleteMapping("/Inventory")
    public Stand_Result DeleteInventory(@RequestParam(value = "id") String id) {
        inventoryService.delete(id);
        Stand_Result result=new Stand_Result();
        result.setSucceed(true);
        result.setWrongCode("0");
        /*未加入操作时间*/
        return result;
    }
    @GetMapping("/Inventory")
    public Book QueryInventory(@RequestParam(value="id") String id){
        return inventoryService.findById(id);
    }


}
