package com.soa.servicecomsumer.remote;

import com.soa.servicecomsumer.pojo.addParams;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemote{
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello" +name+", this message send failed ";
    }
    @Override
    public int add(addParams params) {
        return 0;
    }

}

