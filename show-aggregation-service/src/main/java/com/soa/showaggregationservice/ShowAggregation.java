package com.soa.showaggregationservice;

/**
 * @program: show-aggregation-service
 * @description: show aggregation
 * @author: Yu Liu
 * @create: 2019/11/11
 **/
public class ShowAggregation {
    private String user_id;
    private String user_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return getUser_id()+getUser_name()+" hello";
    }
}