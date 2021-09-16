package demo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo2.bean.OrderBean;
import com.example.demo2.bean.ProductBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderBean> {
    @Select(("select tbl_product.* from tbl_shopping left join tbl_product on tbl_shopping.pid = tbl_product.id where oid = #{oid}"))
    List<ProductBean> getProduct(@Param("oid") int oid);
    @Select(("select * from tbl_order,tbl_product where name = #{name}"))
    List<ProductBean> getHistory(@Param("name") String name);
}
