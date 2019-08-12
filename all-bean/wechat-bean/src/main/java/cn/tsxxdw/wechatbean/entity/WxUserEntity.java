package cn.tsxxdw.wechatbean.entity;

import lombok.Data;

/**
 *
 */
@Data
public class WxUserEntity {
    private String openid;
    private String unionid;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
}
