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
public class BuyProduct {
    private String product_id;
    private String image_url;
    private String name;
    private String uploader_name;
    private int price;
    private String order_id;
    //    buy_products[
//    { string product_id//图书id
//        string image_url//图书图片
//        string name//图书名称
//        string uploader_name//上传者名称
//        int price//图书价格
//    }]
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}