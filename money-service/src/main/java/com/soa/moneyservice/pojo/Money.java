package com.soa.moneyservice.pojo;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Money")
public class Money {

    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "money")
    private int money;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
