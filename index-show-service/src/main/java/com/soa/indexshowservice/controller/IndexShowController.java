package com.soa.indexshowservice.controller;

import com.soa.indexshowservice.pojo.BookInfo;
import com.soa.indexshowservice.pojo.Product;
import com.soa.indexshowservice.pojo.Recieve;
import com.soa.indexshowservice.remote.BookRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: index-show-service
 * @description: controller for index show
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@EnableSwagger2Doc
@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("/v1")
public class IndexShowController {
    @Autowired
    BookRemote bookRemote;

    @GetMapping("/Index")
    public Recieve showIndex(){
        Recieve recieve=new Recieve();
        List<Product> products=new ArrayList<>();
        List<BookInfo> bookInfos=bookRemote.QuerySomeBook(12);
        for(BookInfo bookInfo:bookInfos){
            Product product=new Product();
            product.setId(bookInfo.getId());
            product.setName(bookInfo.getBook_title());
            product.setImage_url(bookInfo.getPicture_url());
            product.setPrice(bookInfo.getBook_price());
            products.add(product);
        }
        recieve.setProduct(products);

        return recieve;
    }
}