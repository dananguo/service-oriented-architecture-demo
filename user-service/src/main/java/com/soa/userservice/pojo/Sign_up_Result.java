package com.soa.userservice.pojo;
import lombok.Data;

@Data
public class Sign_up_Result {
    private String id;
    private  Boolean Succeed;
    private int WrongCode;
}
