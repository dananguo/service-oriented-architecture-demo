package com.soa.authservice.service.impl;

import com.soa.authservice.pojo.AccountInfo;
import com.soa.authservice.pojo.LoginResult;
import com.soa.authservice.remote.AccountRemote;
import com.soa.authservice.service.AuthService;
import com.soa.authservice.library.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 19028
 * @date 2019/12/20 16:34
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountRemote accountRemote;

    /**
     * 用户授权
     *
     * @param account
     * @param password
     * @return
     */

    public LoginResult authentication(String account, String password) {
        LoginResult result = new LoginResult();
        try {
            //1.调用微服务查询用户信息
            AccountInfo accountInfo = accountRemote.QueryAccount(account);
            if (!accountInfo.getAccount().equals("无此账户") && password.equals(accountInfo.getPwd())) {
                result.setWrongCode(0);
                result.setSucceed(true);
                result.setRole(accountInfo.getRole());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                result.setLogin_Time(df.format(new Date()));
                //2.查询结果不为空，则生成token
                String token = JWTUtils.getToken(account);
                result.setToken(token);
            } else {
                result.setLogin_Time(null);
                result.setRole(null);
                result.setSucceed(false);
                result.setWrongCode(1);
                result.setToken(null);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

