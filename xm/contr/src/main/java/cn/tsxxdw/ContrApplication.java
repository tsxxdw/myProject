package cn.tsxxdw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
@MapperScan("cn.tsxxdw.*")
public class ContrApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContrApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate () {
        return  new  RestTemplate ();
    }
}
