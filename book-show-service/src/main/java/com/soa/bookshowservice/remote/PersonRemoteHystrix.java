package com.soa.bookshowservice.remote;

import com.soa.bookshowservice.pojo.PersonInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: user-show-service
 * @description:
 * @author: Yu Liu
 * @create: 2019/11/12
 **/
@Component
public class PersonRemoteHystrix implements PersonRemote {
    @Override
    public PersonInfo QueryPerson(@PathVariable(value = "id") String id){
        return null;
    }

}