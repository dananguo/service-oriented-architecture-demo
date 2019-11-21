package com.soa.showaggregationservice.remote;

import com.soa.showaggregationservice.pojo.PersonInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Cacheable;
import java.util.List;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/11/19
 **/
@Component
public class UserShowRemoteHystrix implements UserShowRemote {
    @Override
    @GetMapping("/user-show-by-id/{id}")
    public PersonInfo showUser(@PathVariable("id") String id)
    {
        return null;
    }

    @Override
    @GetMapping("/user-show-by-name/{name}")
    public List showUserByName(@PathVariable("name") String name){
        return null;
    }

}