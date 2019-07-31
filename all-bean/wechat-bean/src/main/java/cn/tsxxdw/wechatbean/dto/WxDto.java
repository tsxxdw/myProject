package cn.tsxxdw.wechatbean.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class WxDto {
    private String appid;    // 小程序配置文件---------小程序（或者个人id）
    private String appSecret; //小程序配置文件---------密钥



    private String encryptedData; //包括敏感数据在内的完整用户信息的加密数据
    private String iv;
    private String code;


    private String unionid; //代表同一个微信用户
    private String openid; //代表同一个微信小程序用户的身份
    private String session_key;
    private String access_token;

    public String toString() {
        System.out.println(ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE));
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);//按照不同风格打印信息
    }


}
