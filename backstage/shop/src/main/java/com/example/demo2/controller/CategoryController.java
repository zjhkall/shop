package com.example.demo2.controller;

import com.example.demo2.bean.CategoryBean;
import com.example.demo2.mapper.CategoryMapper;
import com.mysql.jdbc.StringUtils;
import org.omg.CORBA.ServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryMapper categoryMapper;
    @GetMapping("/list")
    public String list(String category, HttpServletRequest request){
//        List<CategoryBean> list = categoryMapper.selectList(null);
        request.setAttribute("category", category);
        if(StringUtils.isNullOrEmpty(category)){
            request.setAttribute("retList", categoryMapper.selectList(null));
        }else{
            //模糊查询
            request.setAttribute("tetList", categoryMapper.getLike("%" + category + "%"));
        }

        return "/category/list";
    }
    @GetMapping("/add")
    public String add(Integer id, HttpServletRequest request){
        request.setAttribute("bean", id != null?categoryMapper.selectById(id):null);
        System.out.println("添加操作");
        return "/category/add";
    }
    @PostMapping("/add")
    public String add(CategoryBean bean, HttpServletResponse resp){
        if(bean.getCategory() == null || bean.getCategory().trim().equals("")){
            return jsAlert("请输入类别名称！", ("/category/add" + (bean.getId()!=null?"?id="+bean.getId():"")), resp);
        }
        try{
            if(bean.getId() != null){
                categoryMapper.updateById(bean);
            }else{
                categoryMapper.insert(bean);
            }
        }catch (Exception e){
            return jsAlert(bean.getCategory() + "已经存在了！", ("/category/add" + (bean.getId()!=null?"?id="+bean.getId():"")),resp);
        }
        System.out.println("啊啊啊啊啊啊啊");
//        categoryMapper.insert(bean);
        return "redirect:/category/list";
    }

    @RequestMapping("/del")
    public String del(int id){
        categoryMapper.deleteById(id);
        return "redirect:/category/list";
    }
}
