package com.soa.purchaseservice.pojo;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "logistics")
public class logistics {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    private String log_id;

    @Column(name = "orderid")
    private String orderid;

    @Column(name = "userid")
    private String userid;

}
