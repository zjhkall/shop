package com.example.demo2.controller;

import com.example.demo2.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hot")
public class HotController extends BaseController {
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/list")
    public String list(HttpServletRequest request){
        request.setAttribute("retList", productMapper.getHot());
        return "/hot/list";
    }
}
