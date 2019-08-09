package cn.tsxxdw.service.mylog;

import cn.tsxxdw.myJava.MyOptional;
import cn.tsxxdw.mybean.vo.ResultVo;
import cn.tsxxdw.service.myjson.MyJsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class MyLogUtil {
    public static <T> void logInfo(Class c) {
        logInfo(c,null);
    }
    public static <T> void logInfo(Class c, T t) {
       // Objects.requireNonNull(t);//t如果为null,则会报错
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 3) {//判断是否能获取数组的第三个位置数据
            StackTraceElement stackTraceElement = stackTraceElements[2];
            sb.append("Log site is ");
            sb.append(stackTraceElement.getClassName());
            sb.append(".");
            sb.append(stackTraceElement.getMethodName());
            sb.append(" line:");
            sb.append(stackTraceElement.getLineNumber());
            sb.append(",");
            if(t instanceof String){
                sb.append(t);
            }else {
                MyOptional.ofNullable(stackTraceElement).ifPresent(o -> {//判断获取到的是否为null,不为null则输出下面内容代码
                    ResultVo<String> jsonResultVo = MyJsonUtil.toJsonString(t);
                    if(jsonResultVo.isSuccess()){
                        sb.append(" Json data is ");
                        sb.append(jsonResultVo.getData());
                    }else {
                        logError(c,new Exception("Json conversion exception,please see log"));
                    }
                }, () -> {
                    sb.append("stackTraceElement is null");
                });
            }

        } else {
            log.warn("MyLogUtil.logInfo.warn:{}", "获取不到日志调用点");
        }
        log.info(sb.toString());

    }

    public static <T> void logError(Class c, T t) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 3) {//判断是否能获取数组的第三个位置数据
            StackTraceElement stackTraceElement = stackTraceElements[2];

            Optional.ofNullable(stackTraceElement).ifPresent(o -> {
                sb.append("Error log site is ");
                sb.append(o.getClassName());
                sb.append(".");
                sb.append(o.getMethodName());
                sb.append(" line:");
                sb.append(o.getLineNumber());
                sb.append(",");
            });


        }
        sb.append(" Exception data is :{}");
        log.error(sb.toString(), t);
    }
}
