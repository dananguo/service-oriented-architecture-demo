package com.soa.servicecomsumer.pojo;

import lombok.Data;

@Data
public class addParams {
    private int right;
    private int left;

    public addParams(int left, int right) {
        this.right = right;
        this.left = left;
    }
}
