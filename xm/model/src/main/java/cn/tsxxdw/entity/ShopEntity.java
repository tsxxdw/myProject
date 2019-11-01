package cn.tsxxdw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_shop")
public class ShopEntity extends BaseEntity{
  private String phone;
  private String fullName;
  private  String shopName;
  private String situation;//情况
    private String openid;
}
