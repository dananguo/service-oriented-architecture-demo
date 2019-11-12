package com.soa.userservice.pojo;
import lombok.Data;

@Data
public class LoginResult {
    private boolean Succeed;
    private int WromgCode;
    private String Login_Time;
    private String Role;
}
