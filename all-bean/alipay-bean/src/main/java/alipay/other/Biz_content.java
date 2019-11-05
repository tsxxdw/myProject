package alipay.other;

import lombok.Data;

@Data
public class Biz_content {
    private String body;//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
    private String subject;//商品的标题/交易标题/订单标题/订单关键字等。
    private String out_trade_no;//商户网站唯一订单号
}
