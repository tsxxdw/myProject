package cn.tsxxdw.service.myspringbean;

import org.springframework.beans.BeanUtils;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MyBeanUtils {

    public static <S, T> T copyPropertiesAndResTarget(S source, Supplier<T> supplier) {

        T target = supplier.get();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <S, T> T copyPropertiesAndResTarget(S source, Supplier<T> supplier, Consumer<T> consumer) {
        T target = copyPropertiesAndResTarget(source, supplier);
        consumer.accept(target);
        return target;
    }
}
