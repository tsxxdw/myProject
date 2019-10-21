package cn.tsxxdw.wechatbean.dto;

import lombok.Data;

/**
 *
 */
@Data
public class WxUserDto {
    private String id;
    private String openid;
    private String unionid;
    private String nickName;
    private String phoneNumber;
    private String purePhoneNumber;
    private String countryCode;
    private String avatarUrl ;//微信头像

}
