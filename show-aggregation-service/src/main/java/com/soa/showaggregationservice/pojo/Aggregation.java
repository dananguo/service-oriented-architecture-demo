package com.soa.showaggregationservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @program: show-aggregation-service
 * @description: aggregation of book and its owner
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@Data
public class Aggregation {
    private BookInfo bookInfo;
    private PersonInfo personInfo;
    private Book book;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}