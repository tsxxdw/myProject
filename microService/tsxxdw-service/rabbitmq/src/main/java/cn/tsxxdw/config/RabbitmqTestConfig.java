package cn.tsxxdw.config;


import cn.tsxxdw.other.TypeReference;
import cn.tsxxdw.service.myjson.MyJsonUtil;
import cn.tsxxdw.vo.RabbitmqVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class RabbitmqTestConfig {
    //交换机定义，这里我使用的是direct类型。大家可以根据自己的业务需求来指定对应的。下面会讲几种交换机的类型
    //对应的3个参数1.交换机名称 2.持久性保持标识 3.是否自动删除标识
    public static final String TEST001EXCHANGENAME = "test001ExchangeName"; //交换机
    public static final String TEST001QUEUENAME = "TEST001QUEUENAME"; // 队列名
    public static final String TEST001ROUTINGKEY = "TEST001ROUTINGKEY"; //路由key
     /* @Bean(name = TEST001EXCHANGENAME)
      public DirectExchange directExchange() {
          return new DirectExchange(TEST001EXCHANGENAME, false, false);
      }
      //创建一个队列
      @Bean(name = TEST001QUEUENAME)
      public Queue queue() {
          return QueueBuilder.durable(TEST001QUEUENAME).build();
      }
      //绑定队列到交换机上--with对应的是direct指定的具体key。
      @Bean
      public Binding binding(@Qualifier(TEST001QUEUENAME) Queue queue, @Qualifier(TEST001EXCHANGENAME) DirectExchange exchange) {
          return BindingBuilder.bind(queue).to(exchange).with(TEST001ROUTINGKEY);

      }*/
    @Value("${rabbitmqstr}")
    private String rabbitmqstr;

    @Bean
    public String getJson() {
        try {
            if (StringUtils.isBlank(rabbitmqstr)) return null;
            TypeReference<List<RabbitmqVo>> typeReference = new TypeReference<List<RabbitmqVo>>() {};
            List<RabbitmqVo> result = MyJsonUtil.toList(rabbitmqstr,typeReference.getType());
            result.forEach(o -> {
                DirectExchange exchange = directExchange(o.getExchangeName());
                Queue queue = queue(o.getQueueName());
                binding(queue,exchange,o.getRoutingKey());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public DirectExchange directExchange(String exchange) {
        return new DirectExchange(exchange, false, false);
    }
    //创建一个队列
    @Bean
    public Queue queue(String queuename) {
        return QueueBuilder.durable(queuename).build();
    }
    //绑定队列到交换机上--with对应的是direct指定的具体key。
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange,String routingkey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);

    }

}