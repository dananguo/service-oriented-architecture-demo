package com.soa.indexshowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/19
 **/
@Data
public class Recieve {
    private List<Product> product;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}