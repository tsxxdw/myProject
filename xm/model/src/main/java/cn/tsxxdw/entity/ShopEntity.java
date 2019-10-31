package cn.tsxxdw.entity;

import lombok.Data;

@Data
public class ShopEntity extends BaseEntity{
  private String phone;
  private String fullName;
  private  String shopName;
  private String situation;//情况
}
