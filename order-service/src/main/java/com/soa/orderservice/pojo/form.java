package com.soa.orderservice.pojo;


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
public class form {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "_id")
    private String  orderId;

    @Column(name = "customer_id")
    private String  customerId;

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "state")
    private boolean state;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "single_price")
    private int singlePrice;

    @Column(name = "book_num")
    private int bookNum;

}
