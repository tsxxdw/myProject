package cn.tsxxdw.wechatbean.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
    private String sessionKey;//可以通过code 获取
    private String accessToken;

    private String phoneNumber;
    private String avatarUrl ;//微信头像


    public WxDto copyData(WxDto wxDto) {
        //如果字段是null,则将传入的参数的属性值复制过来
        if (StringUtils.isBlank(this.getAppid())) {
            this.setAppid(wxDto.getAppid());
        }
        if (StringUtils.isBlank(this.getAppSecret())) {
            this.setAppSecret(wxDto.getAppSecret());
        }


        if (StringUtils.isBlank(this.getEncryptedData())) {
            this.setEncryptedData(wxDto.getEncryptedData());
        }
        if (StringUtils.isBlank(this.getIv())) {
            this.setIv(wxDto.getIv());
        }
        if (StringUtils.isBlank(this.getCode())) {
            this.setCode(wxDto.getCode());
        }


        if (StringUtils.isBlank(this.getUnionid())) {
            this.setUnionid(wxDto.getUnionid());
        }
        if (StringUtils.isBlank(this.getOpenid())) {
            this.setOpenid(wxDto.getOpenid());
        }
        if (StringUtils.isBlank(this.getSessionKey())) {
            this.setSessionKey(wxDto.getSessionKey());
        }
        if (StringUtils.isBlank(this.getAccessToken())) {
            this.setAccessToken(wxDto.getAccessToken());
        }
        return this;

    }

    public String toString() {
        System.out.println(ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE));
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);//按照不同风格打印信息
    }


}
