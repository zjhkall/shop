package com.example.demo2.controller;

import com.example.demo2.mapper.OrderMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/list")
    public String list(HttpServletRequest request){
        request.setAttribute("abc", orderMapper.selectList(null));
        return "/order/list";
    }

    @GetMapping("/product")
    public String product(int oid, HttpServletRequest request){
        return "/order/product";
    }
}

