package cn.tsxxdw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 用户下单信息,以及所有跟收费相关的信息(TOrder)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:16
 */
@Data
@TableName("t_user")
public class UserEntity {
    private String id;
    private Date createDate;
    private String identity;
}
