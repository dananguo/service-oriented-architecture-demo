package com.soa.bookshowservice.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @program: finalProject
 * @description:
 * @author: Yu Liu
 * @create: 2019/12/20
 **/
@Data
public class DetailProduct {
    private String name;
    private String image_url;
    private String uploader_name;
    private String price;
    private String detail;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    /*string name //图书名称
string image_url //图片路径
string uploader_name//上传者名称
string detail//图书详细描述
int price//图书价格*/
}