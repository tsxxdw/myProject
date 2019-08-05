package cn.tsxxdw.service.myspringbean;

import org.springframework.beans.BeanUtils;

import java.util.function.Supplier;

public class MyBeanUtils {

    public static <S, T> T copyPropertiesAndResTarget(S source,Supplier<T>supplier) {

        T target=supplier.get();
        BeanUtils.copyProperties(source,target);
        return target;
    }
}
