package cn.tsxxdw.myrabbitmq.service;


import cn.tsxxdw.myrabbitmq.config.RabbitmqTestConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqTestConsumer {
    /**
     * 消费者处理接收消息方法
     * <p>
     * ****重要说明*****
     * 如果生产者是以convertSendAndReceive方法发送，则一定要手动给予返回，处理完后加入下面这一行：
     * ack-true处理：channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
     *               参数说明-------消息id，fasle代表不批量处理（批量是指将消息id小于当前id的都处理掉）
     * ack-false处理：channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
     *               参数说明-------消息id，fasle代表不批量处理（批量是指将消息id小于当前id的都处理掉）,第二个false表示不重新入队(重新入队用true)
     * 拒绝消息：channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); 消息不会重新入队
     *               参数说明-------消息id，fasle表示不重新入队(重新入队用true)
     * 如果不手动返回，则该消息会一直认为没有被消费掉，会一直占用rabbitmq内存空间，时间一久，必然造成内存溢出，切记！！！
     *
     * @param msg 未知
     * @param message 信息
     * @param channel 渠道
     * @throws Exception 异常的信息
     */
    @RabbitListener(queues = RabbitmqTestConfig.TEST001QUEUENAME)
    public void handler(String msg, Message message, Channel channel) throws Exception {
        try {
            System.out.println(msg);
        } catch (Exception e) {
           // log.error(e.toString(), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

}