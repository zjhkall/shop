package com.example.demo2.bean;

import java.util.List;

public class WxResp {
    //响应码，code=1代表成功，code=2代表失败
    public int code = 1;
    //错误提示语，默认没有错误
    public String msg = "";
    //失败函数
    public void fail(String msg){
        this.code = 0;//失败
        this.msg = msg;
    }
    public List<CategoryBean> category;
    public List<ProductBean> product;
    public List<ProductBean> hot;
}
