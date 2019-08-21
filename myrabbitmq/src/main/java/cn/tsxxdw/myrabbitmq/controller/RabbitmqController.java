package cn.tsxxdw.myrabbitmq.controller;

import cn.tsxxdw.myrabbitmq.service.RabbitmqTestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RabbitmqController {
    @Autowired
    private RabbitmqTestProducer rabbitmqTestProducer;
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        rabbitmqTestProducer.sendSMSMessage("20180906");
        return "success";
    }
}
