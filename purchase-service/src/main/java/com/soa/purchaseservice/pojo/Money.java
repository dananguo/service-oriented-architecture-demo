package com.soa.purchaseservice.pojo;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "Money")
public class Money {

    @Id
    @Column(name = "_id")
    private String Id;

    @Column(name = "money")
    private int Money;
}
