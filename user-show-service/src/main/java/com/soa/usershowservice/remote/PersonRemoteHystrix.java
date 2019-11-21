package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.PersonInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    @Override
    public List QueryPersonByName(@PathVariable(value="name") String name){
        return null;
    }
}