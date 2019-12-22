package com.soa.usershowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/21
 **/
@Data
public class Result {
    private String user_id;
    private String user_name;
    private int total_money;
    private List<UploadProduct> upload_products;
    private List<BuyProduct> buy_products;

//    string user_id //用户id
//    string user_name //用户名
//    int total_money//账户余额

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}