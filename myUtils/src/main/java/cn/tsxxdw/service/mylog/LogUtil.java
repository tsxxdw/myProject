package cn.tsxxdw.service.mylog;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class LogUtil {
    public static <T> void logInfo(String message, T t) {
        Optional.ofNullable(t).ifPresent(o -> {
            log.info(message + ".info:{}", JSON.toJSONString(t));
        });
    }
}
