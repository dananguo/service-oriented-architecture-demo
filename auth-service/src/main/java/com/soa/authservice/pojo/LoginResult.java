package com.soa.authservice.pojo;

import lombok.Data;

@Data
public class LoginResult {
    private boolean Succeed;
    private int wrongCode;
    private String Login_Time;
    private String Role;
    private String token;
}
