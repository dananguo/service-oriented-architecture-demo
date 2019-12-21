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
public class DetailResult {
    private DetailProduct product;
    private String resultDescribe;
    private boolean result;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}