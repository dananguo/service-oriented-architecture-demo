package com.soa.usershowservice.remote;

import com.soa.usershowservice.pojo.Money;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/22
 **/
@Component
public class MoneyRemoteHystrix implements MoneyRemote{
    @GetMapping("/v1/Money/{userid}")
    public Money GetMoney(@PathVariable("userid") String id){
        return null;
    }
}