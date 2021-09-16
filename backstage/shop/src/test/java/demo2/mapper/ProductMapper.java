package demo2.mapper;

        import com.baomidou.mybatisplus.core.mapper.BaseMapper;
        import com.example.demo2.bean.ProductBean;
        import org.apache.ibatis.annotations.Param;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;

public interface ProductMapper extends BaseMapper<ProductBean> {
    //根据类别id查询该类别下对应的商品列表
    @Select("select tbl_product.*, tbl_category.category "
            + "from tbl_product left join tbl_category "
            + "on tbl_product.cid = tbl_category.id "
            + "where cid = #{cid}")
    List<ProductBean> getProduct(@Param("cid") int cid);

    @Select("select * from tbl_product where hot=1")
    List<ProductBean> getHot();
    @Select("select * from tbl_product where id = #{id}")
    ProductBean getP(@Param("id") int id);
}
