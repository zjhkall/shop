package demo2.controller;

import com.example.demo2.App;
import com.example.demo2.controller.BaseController;
import com.example.demo2.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/hot")
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={App.class})
public class HotController extends BaseController {
    @Autowired
    private ProductMapper productMapper;
    @GetMapping("/list")
    @Test
    public void list(){//HttpServletRequest request
        int a = 5;
        a = 56;
        a = 77;
        //request.setAttribute("retList", productMapper.getHot());
        System.out.println(a);
        //return "/hot/list";
    }
}
