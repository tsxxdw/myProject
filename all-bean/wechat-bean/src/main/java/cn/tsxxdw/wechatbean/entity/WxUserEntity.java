package cn.tsxxdw.wechatbean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 */
@Data
@TableName("t_user")
public class WxUserEntity {
    private String id;
    private String openid;
    private String unionid;
    private String nickName;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private String avatarUrl ;//微信头像
}
