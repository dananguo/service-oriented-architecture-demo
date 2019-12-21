package com.soa.inventoryservice.pojo;

public class PurchaseParam {
    public String Book_id;
    public String User_id;
    public int Num;
    public int price;
    public String Order_id;//仅在取消购买时有效
}
