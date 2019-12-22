package com.soa.uploadservice.pojo;
import lombok.Data;
@Data
public class UploadParam {
    public String book_title;
    public String book_type;
    public String picture_url;
    public String publisher;
    public String author;
    public String describe;
    public String string_price;
    public String user_id;
    public int book_price;
    public int inventory_quantity;
}
