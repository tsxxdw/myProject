package cn.tsxxdw.service.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflexUtil {
    public static String getFieldType(Field filed) {
        return filed.getGenericType().toString();
    }

    public static String getFieldName(Field filed) {
        return filed.getName();
    }


    public static Object getValue(Object object,Field filed) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String filedName = getFieldName(filed);
        String methodName = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        Method m = object.getClass().getMethod("get" + methodName);
        Object value = m.invoke(object); //调用getter方法获取属性值
        return value;
    }

}
