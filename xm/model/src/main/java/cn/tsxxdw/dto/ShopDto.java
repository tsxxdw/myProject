package cn.tsxxdw.dto;

import lombok.Data;

@Data
public class ShopDto {
  private String id ;
  private String phone;
  private String fullName;
  private  String shopName;
  private String situation;//情况
  private String number;//编号
  private String callStatus;//拨打状态
  private String callDate;//拨打时间
  private String createDate;
  private String updateDate;
}
