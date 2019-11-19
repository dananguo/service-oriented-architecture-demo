package com.soa.showaggregationservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;


@Data
public class PersonInfo {
    private String id;
    private String Name;
    private int Age;
    private String Sex;
    private String Country;
    private String Signature;
    private String Phone;
    private String E_mail;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}