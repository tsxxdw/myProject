package alipay.other;

import lombok.Data;

@Data
public class OrderInfo {

    private String    app_id	;//	支付宝分配给开发者的应用ID	2014072300007148
    private String   method	;//		是	128	接口名称	alipay.trade.app.pay
    private String   format	;//		否	40	仅支持JSON	JSON
    private String   charset	;//		是	10	请求使用的编码格式，如utf-8,gbk,gb2312等	utf-8
    private String   sign_type	;//		是	10	商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2	RSA2
    private  String  sign	;//		是	256	商户请求参数的签名串，详见签名	详见示例
    private  String  timestamp	;//		是	19	发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"	2014-07-24 03:07:50
    private  String  version	;//		是	3	调用的接口版本，固定为：1.0	1.0
    private String   notify_url	;//		是	256	支付宝服务器主动通知商户服务器里指定的页面 http/https 路径。建议商户使用 https	https://api.xx.com/receive_notify.htm
    private String   biz_content	;//		是	-	业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档

}
