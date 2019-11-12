package com.soa.accountservice.pojo;

import lombok.Data;

@Data
public class Sign_up_params {
    private String Account;
    private  String Pwd;
    private  String Role;
}
