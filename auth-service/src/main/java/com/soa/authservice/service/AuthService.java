package com.soa.authservice.service;

import com.soa.authservice.pojo.LoginResult;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    /**
     * 用户授权
     *
     * @param account
     * @param password
     * @return
     */
    LoginResult authentication(String account, String password);
}
