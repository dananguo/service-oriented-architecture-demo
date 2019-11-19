package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.PersonInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: user-show-service
 * @description:
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@Component
public class PersonRemoteHystrix implements PersonRemote {
    @Override
    public PersonInfo QueryPerson(@RequestParam(value = "id") String id){
        return null;
    }

}