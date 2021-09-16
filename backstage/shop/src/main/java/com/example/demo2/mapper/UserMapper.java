package com.example.demo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo2.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//接口继承接口，继承BaseMapper后，就可以实现简单的增删改查
//复杂的增删改查，就只能写SQL语句
public interface UserMapper extends BaseMapper<UserBean> {
    @Select("select * from tbl_user where username=#{username} and password=#{password}")
    UserBean getUser(@Param("username") String username, @Param("password") String password);
}
