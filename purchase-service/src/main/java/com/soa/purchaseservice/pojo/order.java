package com.soa.purchaseservice.pojo;


import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity(name="form")
public class order {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "order_id")
    private String  order_id;

    @Column(name = "customerid")
    private String  customerid;

    @Column(name = "book_id")
    private String  book_id;

    @Column(name = "state")
    private boolean state;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "single_price")
    private int single_price;

    @Column(name = "book_num")
    private int book_num;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
