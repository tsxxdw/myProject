package cn.tsxxdw.wechatbean.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class WxDto {
    private String encryptedData; //包括敏感数据在内的完整用户信息的加密数据
    private String iv;
    private String code;
    private String appid;    // 该属性应该是从配置文件读取过来的
    private String appSecret; //秘钥(该属性应该是从配置文件读取过来的)
    private String unionId; //代表同一个微信用户
    private String  openId; //代表同一个微信小程序用户

    public String toString() {
        System.out.println(ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE));
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);//按照不同风格打印信息
    }


}
