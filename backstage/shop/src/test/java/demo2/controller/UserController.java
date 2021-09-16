package demo2.controller;

import com.example.demo2.bean.UserBean;
import com.example.demo2.controller.BaseController;
import com.example.demo2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

//Controller就是 SpringMVC
@Controller
public class UserController extends BaseController {
    @Autowired //创建对象
    private UserMapper userMapper;
    @RequestMapping("/login")
    public String login(String username, String password){
        UserBean user = userMapper.getUser(username, password);
        if(user != null){
            return "redirect:/main？uid=" + user.getId();
        }else{
            return "redirect:/index.html?msg=" + getUTF8Param("用户名或密码错误");
        }
    }
    @RequestMapping("/main")
    public String main(int uid, HttpServletRequest request){
        UserBean bean = userMapper.selectById(uid);
        String username = bean.getUsername();
        request.setAttribute("username", username);
        return "/main";
    }
}




