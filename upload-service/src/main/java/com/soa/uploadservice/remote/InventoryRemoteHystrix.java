package com.soa.uploadservice.remote;

import com.soa.uploadservice.pojo.Book;
import com.soa.uploadservice.pojo.Stand_Result;
import org.springframework.stereotype.Component;

@Component
public class InventoryRemoteHystrix implements InventoryRemote {
    @Override
    public String NewInventory(Book book) {
        return "调用Inventory基础服务失败";
    }

    @Override
    public Stand_Result Update(Book book) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return null;
    }

    @Override
    public Stand_Result DeleteInventory(String id) {
        Stand_Result result=new Stand_Result();
        result.setSucceed(false);
        result.setWrongCode("1");
        return null;
    }
}
