package com.soa.purchaseservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @author zhangyc
 * @date 2019/11/10 16:22
 */
@Data
@Document(collection = "bookInfo")
public class BookInfo {
    @Id
    private String id;
    private String book_id;
    private String book_title;
    private String book_type;
    private String picture_url;
    private String publisher;
    private String author;
    private String describe;
    private String book_price;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
