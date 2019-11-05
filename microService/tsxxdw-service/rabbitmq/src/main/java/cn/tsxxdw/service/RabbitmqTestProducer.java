package cn.tsxxdw.service;
import cn.tsxxdw.config.RabbitmqTestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Date;

@Slf4j
@Component
public class RabbitmqTestProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化确认发送回调及发送返回回调
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 实现消息发送到RabbitMQ交换器后接收ack回调
     * @param correlationData 未知
     * @param ack 未知
     * @param cause 未知
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){ // 发送成功
            log.info("trainLink message send success ---" + new Date());
        } else { // 发送失败
            log.error("trainLink message send failed because ---" + cause);
        }
    }

    /**
     * 实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调
     * @param message  消息
     * @param replyCode 未知
     * @param replyText 未知
     * @param exchange 交换机
     * @param routingKey 路由key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
       // log.error(message.getMessageProperties().getCorrelationIdString() + " send failed:error code " + replyCode + "mains:" + replyText);
    }

    /**
     * 发送消息，供外部调用
     * ****** 重要 ******说明：发送时的方法选择
     * ****** 重要 ******convertAndSend属于不要求返回确认的
     * ****** 重要 ******convertSendAndReceive要求返回确认
     * ****** 重要 ******大家根据不同的业务场景进行选择，
     * 不返回确认可以理解为全异步；
     * 返回确认可以理解为异步处理，同步返回，存在一个生产者等待消费者的问题
     * 选择的原则一般为一致性要求较强的，要确认返回；
     * 一致性不强的，使用不返回确认，加大处理效率,免去等待时间
     * @param msg 要发送到rabbitmq消息对了的信息
     */
    public void sendSMSMessage(String msg){
        // fanout类型的交换器不需要routingkey，我这里用的是direct所以指定了对应的routingkey
        this.rabbitTemplate.convertAndSend(RabbitmqTestConfig.TEST001EXCHANGENAME,RabbitmqTestConfig.TEST001ROUTINGKEY, msg);
    }

   /* @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {

    }*/
}