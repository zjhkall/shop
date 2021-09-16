package demo2.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//一个bean对应一个数据库表
@TableName("tbl_shopping")
public class ShoppingBean{
    @TableId(value = "id", type = IdType.AUTO) //id是主键且是自增的
    private Integer id;
    private Integer oid;
    private Integer pid;
    private Integer count;
    //生成构造方法,有参构造方法

    public ShoppingBean(Integer oid, Integer pid, Integer count) {
        this.oid = oid;
        this.pid = pid;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return oid;
    }

    public void setCid(Integer cid) {
        this.oid = cid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}