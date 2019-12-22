package com.soa.usershowservice.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Money")
public class Money {

    @Id
    @Column(name = "_id")
    private String Id;

    @Column(name = "money")
    private int money;

}
