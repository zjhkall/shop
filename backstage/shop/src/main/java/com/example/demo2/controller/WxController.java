package com.example.demo2.controller;

import com.example.demo2.bean.OrderBean;
import com.example.demo2.bean.ProductBean;
import com.example.demo2.bean.ShoppingBean;
import com.example.demo2.bean.WxResp;
import com.example.demo2.mapper.CategoryMapper;
import com.example.demo2.mapper.OrderMapper;
import com.example.demo2.mapper.ProductMapper;
import com.example.demo2.mapper.ShoppingMapper;
import com.example.demo2.util.NotNullUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wx")
public class WxController extends BaseController{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShoppingMapper shoppingMapper;
    //localhost:8080/wx/index
    @GetMapping("/index")
    public void index(Integer cid, HttpServletResponse resp){//查询种类信息
        WxResp r = new WxResp();
        r.category = categoryMapper.selectList(null);
        if(!r.category.isEmpty()){
            r.product = cid!=null ? productMapper.getProduct(cid):
                    productMapper.getProduct(r.category.get(0).getId());
        }
        r.hot = productMapper.getHot();
        outRespJson(r,  resp);//可以把一个对象转换成json字符串输出到浏览器或小程序中
    }
    @PostMapping("/order") //小程序提交订单
    public void order(OrderBean bean, HttpServletResponse resp){
        //json字符串转换成对象或者数组，固定写法，使用谷歌研发的gson jar包
        List<ProductBean> product = new Gson().fromJson(bean.getJson(), new TypeToken<List<ProductBean>>() {}.getType());
        WxResp r = new WxResp();
        String alert = NotNullUtil.isBlankAlert(bean);
        if(alert != null){//有错误
            r.fail(alert);//失败了
        }else if(product.isEmpty()){
            r.fail("购物车里空空如也");
        }else{
            bean.setCtime(new Date());
            orderMapper.insert(bean);//把新订单添加到列表中
            for(ProductBean p : product){
                ShoppingBean s = new ShoppingBean(bean.getId(), p.getId(), p.getCount());
                shoppingMapper.insert(s);
                ProductBean pb = productMapper.getP(p.getId());
                pb.setNum(pb.getNum() - p.getCount());
                productMapper.updateById(pb);
            }
        }
        outRespJson(r, resp);
    }
}
