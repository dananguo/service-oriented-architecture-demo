package com.soa.purchaseservice.pojo;

import lombok.Data;

@Data
public class Stand_Result {
    private boolean Succeed;
    private String WrongCode;
    private String Time;
}
