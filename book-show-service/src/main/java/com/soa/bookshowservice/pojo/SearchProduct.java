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
    String id;
    String name;
    String image_url;
    String price;
    String uploader_name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}