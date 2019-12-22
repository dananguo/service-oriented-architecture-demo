package com.soa.inventoryservice.pojo;

import lombok.Data;

@Data
public class Stand_Result {
    private boolean Succeed;
    private String WrongCode;
    private String Time;
    private String ErrorMessage;
    private String Message;
}
