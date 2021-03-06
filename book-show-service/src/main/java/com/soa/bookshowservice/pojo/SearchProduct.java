package com.soa.bookshowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/20
 **/
@Data
public class SearchProduct {
    private String id;
    private String name;
    private String image_url;
    private String price;
    private String uploader_name;
    private String type;
    private String publisher;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}