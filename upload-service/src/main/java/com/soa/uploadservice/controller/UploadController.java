package com.soa.uploadservice.controller;

import com.soa.uploadservice.pojo.Book;
import com.soa.uploadservice.pojo.BookInfo;
import com.soa.uploadservice.pojo.Stand_Result;
import com.soa.uploadservice.pojo.UploadParam;
import com.soa.uploadservice.remote.BookRemote;
import com.soa.uploadservice.remote.InventoryRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@EnableSwagger2Doc
@RestController
public class UploadController {
    @Autowired
    BookRemote bookRemote;
    @Autowired
    InventoryRemote inventoryRemote;

    @PostMapping("/Upload")
    public Stand_Result uploadbook(@RequestBody UploadParam uploadParam)
    {
        //将参数拆分成两个部分
        Book book=new Book();
        BookInfo bookInfo=new BookInfo();
        book.setUser_id(uploadParam.getUser_id());
        book.setBook_price(uploadParam.getBook_price());
        book.setInventory_quantity(uploadParam.getInventory_quantity());
        String  id=inventoryRemote.NewInventory(book);


        bookInfo.setId(id);
        bookInfo.setBook_id(book.getBook_id());
        bookInfo.setBook_title(uploadParam.getBook_title());
        bookInfo.setBook_type(uploadParam.getBook_type());
        bookInfo.setPicture_url(uploadParam.getPicture_url());
        bookInfo.setPublisher(uploadParam.getPublisher());
        bookInfo.setAuthor(uploadParam.getAuthor());
        bookInfo.setDescribe(uploadParam.getDescribe());
        bookInfo.setBook_price(uploadParam.getString_price());

        //调用remote，然后得出结果
         bookRemote.NewBook(bookInfo);


        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @PutMapping("/Inventory")
    public Stand_Result UpdateInventory(@RequestBody Book book)
    {
        inventoryRemote.Update(book);
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @PutMapping("/BookInfo")
    public Stand_Result UpdateBook(@RequestBody BookInfo bookInfo)
    {
        bookRemote.Update(bookInfo);
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }

    @DeleteMapping("/Upload")
    public Stand_Result DeleteBook(@RequestParam(value = "id") String id)
    {
        bookRemote.DeleteBook(id);
        inventoryRemote.DeleteInventory(id);

        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        return result;
    }
}
