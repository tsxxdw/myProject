package cn.tsxxdw.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细表(TOrderDetail)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:17
 */
@Data
public class OrderDetailEntity implements Serializable {
    private static final long serialVersionUID = -98449350963458992L;
    
    private String id;
    
    private String orderId;
    //客户拍的图片url(json)
    private String picUrl;
    //邮箱地址，有什么事情会发邮箱
    private String mail;
    //需要拆除的东西
    private String dismantleThings;
    //贵重的物品
    private String preciousThings;
    //家具
    private String furniture;
    //付款时间
    private Date payMoneyDate;
    //实际付款money
    private String actualPayment;
    //备注
    private String remark;




}