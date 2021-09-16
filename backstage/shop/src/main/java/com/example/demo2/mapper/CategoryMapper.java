package com.example.demo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo2.bean.CategoryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Locale;

public interface CategoryMapper extends BaseMapper<CategoryBean> {
    //增删改查不需要写SQL语句，Mapper层以及继承关系必须要有
    @Select("select * from tbl_category where category like #{category}")
    List<CategoryBean> getLike(@Param("category")String category);
}
