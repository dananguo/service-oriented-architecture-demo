package com.soa.usershowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author zhangyc
 * @date 2019/11/10 16:11
 */
@Data
@Entity(name = "Book")
public class Book {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "book_id")
    private String book_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "book_price")
    private double book_price;

    @Column(name = "inventory_quantity")
    private int inventory_quantity;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
