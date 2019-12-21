package com.soa.bookshowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/20
 **/
@Data
public class SearchResult {
    private List<SearchProduct> product;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}