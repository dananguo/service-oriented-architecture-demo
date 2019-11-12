package com.soa.personservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.annotation.Documented;
import java.sql.Timestamp;

/**
 * @author zhangyc
 * @date 2019/11/5 23:31
 */
@Data
@Entity(name = "User")

public class Person {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "_id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Age")
    private int age;

    @Column(name = "Sex")
    private String sex;

    @Column(name = "CreateTime")
    @CreatedDate
    private Timestamp createTime;

    @Column(name = "Country")
    private String country;

    @Column(name = "Signature")
    private String signature;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "E_mail")
    private String email;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
