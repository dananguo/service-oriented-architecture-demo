package com.soa.accountservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhangyc
 * @date 2019/11/10 16:17
 */
@Data
@Entity(name = "Account")
public class Account {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(name = "_id")
    private String _id;

    @Column(name = "Account")
    private String account;

    @Column(name = "Pwd")
    private String pwd;

    @Column(name = "Role")
    private String role;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
