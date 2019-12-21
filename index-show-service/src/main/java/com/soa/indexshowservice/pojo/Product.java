package com.soa.indexshowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/20
 **/
@Data
public class Product {
    private String id;
    private String name;
    private String image_url;
    private String price;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}