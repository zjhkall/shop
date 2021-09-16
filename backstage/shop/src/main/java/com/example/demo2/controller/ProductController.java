package com.example.demo2.controller;

import com.example.demo2.bean.ProductBean;
import com.example.demo2.mapper.ProductMapper;
import com.example.demo2.util.NotNullUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/list")
    public String list(Integer cid, HttpServletRequest request){//返回产品列表
        request.setAttribute("retList", productMapper.getProduct(cid));
        request.setAttribute("cid", cid);
        return "/product/list";
    }

    @GetMapping("add")//添加产品1
    public String add(Integer id, int cid, HttpServletRequest request){
        request.setAttribute("cid", cid);
        request.setAttribute("bean", id != null ? productMapper.selectById(id):null);
        return "/product/add";
    }
    @PostMapping("add")//添加产品2
    public String add(ProductBean bean, HttpServletResponse resp){
        if(NotNullUtil.isBlank(bean)){
            return jsAlert("请完善商品信息！",
                    "/product/add?cid=" + bean.getCid() +
                            (bean.getId()!= null ? "&id=" +bean.getId():""),resp);
        }
        bean.setPrice(Math.abs(bean.getPrice()));
        bean.setNum(Math.abs(bean.getNum()));
        if(bean.getId() != null){
            productMapper.updateById(bean);
        }else{
            productMapper.insert(bean);
        }//bean里面有cid，你得检查一下表单有没有提交cid过来
        return "redirect:/product/list?cid=" + bean.getCid();
    }
    @PostMapping("/update")//更新产品
    public String update(ProductBean bean, HttpServletResponse response){
        productMapper.updateById(bean);
        return "redirect:/product/list?cid=" + bean.getCid();
    }
    @GetMapping("/del")//删除产品
    public String del(int id, int cid){
        productMapper.deleteById(id);
        return "redirect:/product/list?cid=" + cid;
    }
    @RequestMapping("/logo")//上传商品图片
    public void logo(MultipartFile file, HttpServletResponse resp){
        String filename=file.getOriginalFilename();
        System.out.println(filename);
        try {
            file.transferTo(new File("D:/create/shop/file/" + filename));
        } catch (IOException e) {
            System.out.println("检查一下路径问题");
        }
        outRespJson("/shop/file/" + filename, resp);
    }
}
