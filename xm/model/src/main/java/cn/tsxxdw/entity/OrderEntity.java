package cn.tsxxdw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户下单信息,以及所有跟收费相关的信息(TOrder)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:16
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 167308788972962144L;
    
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
    //上去的电梯是几层
    private Integer startLadder;
    //下去的楼梯是几层
    private Integer endLadder;
    //根据客户的信息评估的费用
    private Integer assessmentCosts;
    //付款状态1 定金2 部分付款3 全额付款
    private String payMoneyStatus;
    //套餐

    private String setMeal;

    private Date carryDate;
    //创建时间
    private Date createDate;

    //车辆类型
      private String carType;

}