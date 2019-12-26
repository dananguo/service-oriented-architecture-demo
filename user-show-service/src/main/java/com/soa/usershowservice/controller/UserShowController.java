package com.soa.usershowservice.controller;

import com.soa.usershowservice.pojo.*;
import com.soa.usershowservice.remote.*;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@EnableSwagger2Doc
@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/v1")
public class UserShowController {
    @Autowired
    PersonRemote personRemote;
    @Autowired
    InventoryRemote inventoryRemote;
    @Autowired
    MoneyRemote moneyRemote;
    @Autowired
    OrderRemote orderRemote;
    @Autowired
    BookRemote bookRemote;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/user-show-by-id")
    public Result showUser(){
        String  id=request.getHeader("uid");
        Result result=new Result();
        PersonInfo personInfo=personRemote.QueryPerson(id);
        List<UploadProduct> uploadProducts=new ArrayList<>();
        if(personInfo==null) {
            return result;
        }
        else{
            result.setUser_id(id);
            result.setUser_name(personInfo.getName());
        }
        Money money=moneyRemote.GetMoney(id);
        result.setTotal_money(money.getMoney());
        List<Book> books=inventoryRemote.QueryInventoryByUserId(id);

        for(Book book:books){
            UploadProduct uploadProduct=new UploadProduct();
            String bookId=book.getBook_id();
            BookInfo info = bookRemote.QueryBook(bookId);
            uploadProduct.setProduct_id(bookId);
            uploadProduct.setImage_url(info.getPicture_url());
            uploadProduct.setName(info.getBook_title());
            uploadProduct.setPrice(book.getBook_price());
            uploadProducts.add(uploadProduct);
        }
        result.setUpload_products(uploadProducts);
        List<BuyProduct> buyProducts=new ArrayList<>();
        List<form> orders=orderRemote.FingOrderByID(id);
        for(form order:orders){
            BuyProduct buyProduct=new BuyProduct();
                String product_id=order.getBookId();
                buyProduct.setPrice(order.getSinglePrice()*order.getBookNum());
                buyProduct.setProduct_id(product_id);
                BookInfo book=bookRemote.QueryBook(product_id);
                buyProduct.setUploader_name(personInfo.getName());
                buyProduct.setOrder_id(order.getOrderId());
                if(book==null){
                    buyProduct.setImage_url("未找到该书。");
                    buyProduct.setName("未找到该书。");

                }else{
                    buyProduct.setImage_url(book.getPicture_url());
                    buyProduct.setName(book.getBook_title());
                }
                buyProducts.add(buyProduct);

            }
            result.setBuy_products(buyProducts);


        return result;



    }
    @GetMapping("/user-show-by-name/{name}")
    public List showUserByName(@PathVariable("name") String name){
        return personRemote.QueryPersonByName(name);
    }
}