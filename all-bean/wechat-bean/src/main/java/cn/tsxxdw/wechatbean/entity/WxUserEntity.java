package cn.tsxxdw.wechatbean.entity;

import lombok.Data;

/**
 *
 */
@Data
public class WxUserEntity {
    private String openid;
    private String unionid;
    private String nickName;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private String avatarUrl ;//微信头像
}
