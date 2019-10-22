package cn.tsxxdw.dto;

import lombok.Data;

/**
 * 用户下单信息(TOrder)实体类
 *
 * @author makejava
 * @since 2019-08-09 22:56:51
 */
@Data
public class OrderDto  {


    private String id;
    //小程序用户身份id
    private String openid;
    //姓名
    private String fullName;
    //电话
    private String phone;
    //老家地址

    private String startAddress;
    //新家地址
    private String endAddress;
    private Integer startLadder;
    private Integer endLadder;
    //根据客户的信息评估的费用
    private Integer assessmentCosts;
    //付款状态1 定金2 部分付款3 全额付款
    private String payMoneyStatus;
    //套餐

    private String setMeal;

    //搬运的时间
    private String carryDate;
    //创建时间
    private String createDate;
    //车辆类型
    private String carType;

}