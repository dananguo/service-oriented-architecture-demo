package com.soa.purchaseservice.param;


import lombok.Data;

@Data
public class PayPrama {
    public String orderId;
    public String userId;
    public int totalPrice;
}
