package com.soa.userservice.service;

import com.soa.userservice.pojo.LoginParams;

public interface LoginService {
    boolean authenticate(LoginParams params);
}
