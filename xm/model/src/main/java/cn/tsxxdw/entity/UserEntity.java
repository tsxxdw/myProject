package cn.tsxxdw.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户表(TUser)实体类
 *
 * @author makejava
 * @since 2019-08-15 23:10:17
 */
@Data
@TableName("t_user")
public class UserEntity {
    private String id;
    private Date createDate;
    private String identity;
}
