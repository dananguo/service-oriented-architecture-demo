package com.soa.uploadservice.pojo;
import lombok.Data;
@Data
public class UploadParam {
    private String book_id;
    private String book_title;
    private String book_type;
    private String picture_url;
    private String publisher;
    private String author;
    private String describe;
    private String string_price;
    private String user_id;
    private double book_price;
    private int inventory_quantity;
}
