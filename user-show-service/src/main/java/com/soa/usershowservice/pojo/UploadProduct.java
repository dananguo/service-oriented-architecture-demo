package com.soa.usershowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/21
 **/
@Data
public class UploadProduct {
    private String product_id;
    private String image_url;
    private String name;
    private double price;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}