package com.soa.userservice.service.impl;

import com.soa.userservice.pojo.LoginParams;
import com.soa.userservice.pojo.usersFromConfigServer;
import com.soa.userservice.pojo.usersFromConfigServer.userInfo;
import com.soa.userservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RefreshScope
public class loginServiceImpl implements LoginService {
    private final usersFromConfigServer users;
    private static Logger logger = LoggerFactory.getLogger(loginServiceImpl.class);
    public loginServiceImpl(usersFromConfigServer users) {
        this.users = users;
    }

    @Override
    public boolean authenticate(LoginParams params) {
        List<userInfo> list = users.getList();
        logger.info("The Length of List  {}",list.size());
        logger.info("username {}, password {}",params.getUserName(),params.getPassword());
        for (userInfo info : list) {
            if(params.getUserName().equals(info.getUsername())&&
            params.getPassword().equals(info.getPassword())){
                return true;
            }
        }
        return false;
    }
}
