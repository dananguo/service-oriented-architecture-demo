package com.soa.bookshowservice.controller;


import com.soa.bookshowservice.pojo.*;
import com.soa.bookshowservice.remote.BookRemote;
import com.soa.bookshowservice.remote.InventoryRemote;
import com.soa.bookshowservice.remote.PersonRemote;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: finalProject
 * @description: controller for book show  service
 * @author: Yu Liu
 * @create: 2019/11/14
 **/
@EnableSwagger2Doc
@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/v1")
public class BookShowCotroller {

        @Autowired
        BookRemote bookRemote;

        @Autowired
        PersonRemote personRemote;

        @Autowired
        InventoryRemote inventoryRemote;


        @GetMapping("/book-information-by-id/{id}")
        public DetailResult bookInfoShowById(@PathVariable(value = "id") String id)
        {
                DetailResult detailResult=new DetailResult();
                DetailProduct detailProduct=new DetailProduct();

                BookInfo bookInfo=bookRemote.QueryBook(id);
                if(bookInfo==null){
                        detailResult.setResult(false);
                        detailResult.setResultDescribe("未找到该书。");
                        return detailResult;

                }
                detailProduct.setName(bookInfo.getBook_title());
                detailProduct.setImage_url(bookInfo.getPicture_url());
                detailProduct.setDetail(bookInfo.getDescribe());
                detailProduct.setPrice(bookInfo.getBook_price());
                Book inventory=inventoryRemote.QueryInventory(id);
                if(inventory==null){
                        detailResult.setResult(false);
                        detailResult.setResultDescribe("库存中不存在该书。");
                        return detailResult;
                }
                else{
                        PersonInfo user=personRemote.QueryPerson(inventory.getUser_id());
                        if(user==null){
                                detailResult.setResult(false);
                                detailResult.setResultDescribe("不存在该上传者。");
                                return detailResult;
                        }
                        detailProduct.setUploader_name(user.getName());

                }
                detailResult.setResultDescribe("成功。");
                detailResult.setResult(true);
                detailResult.setProduct(detailProduct);

                return detailResult;
        }


        @GetMapping("/book-information-by-title/{title}")
        public SearchResult bookInfoShowByTitle(@PathVariable(value = "title") String title)
        {
                List<SearchProduct> searchProducts=new ArrayList<>();
                List<BookInfo> bookInfos=bookRemote.QueryBookByTitle(title);
                for(BookInfo bookInfo:bookInfos){
                        SearchProduct searchProduct=new SearchProduct();
                        searchProduct.setName(title);
                        String id=bookInfo.getId();
                        searchProduct.setId(id);
                        searchProduct.setImage_url(bookInfo.getPicture_url());
                        searchProduct.setPrice(bookInfo.getBook_price());
                        Book inventory=inventoryRemote.QueryInventory(id);
                        if(inventory==null)
                                searchProduct.setUploader_name("库存中不存在该书。");
                        else{
                                PersonInfo user=personRemote.QueryPerson(inventory.getUser_id());
                                if(user==null)
                                        searchProduct.setUploader_name("不存在该上传者。");
                                else
                                        searchProduct.setUploader_name(user.getName());

                        }
                        searchProducts.add(searchProduct);

                }
                SearchResult searchResult=new SearchResult();
                searchResult.setProduct(searchProducts);
                return searchResult;
        }




}
