package com.soa.accountservice.pojo;
import lombok.Data;

@Data
public class LoginParams {
    private String Account;
    private String Pwd;
}
