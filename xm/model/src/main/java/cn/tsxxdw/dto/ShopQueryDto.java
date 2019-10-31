package cn.tsxxdw.dto;

import lombok.Data;

@Data
public class ShopQueryDto extends BaseQueryDto{
  private String id ;
  private String phone;
  private String fullName;
  private  String shopName;
  private String situation;//情况
  private String openid;
}
