package com.soa.userservice.remote;

import com.soa.userservice.pojo.PersonInfo;
import com.soa.userservice.pojo.Stand_Result;
import org.springframework.stereotype.Component;

@Component
public class PersonRemoteHystrix implements PersonRemote{
    @Override
    public Stand_Result NewPerson(PersonInfo personInfo) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return result;
    }

    @Override
    public PersonInfo QueryPerson(String id) {
        PersonInfo personInfo=new PersonInfo();
        personInfo.setId("调用Person基础服务失败");

        return personInfo;
    }

    @Override
    public Stand_Result Update(PersonInfo personInfo) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return result;
    }

    @Override
    public Stand_Result Delete(String id) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return result;
    }
}
