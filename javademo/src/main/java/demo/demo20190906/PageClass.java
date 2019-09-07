package demo.demo20190906;

import cn.tsxxdw.service.mystr.MyStrUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class PageClass<E> {

    public static void testReflect(Object model) throws Exception {
        Field[] field = model.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
        for (int j = 0; j < field.length; j++) { //遍历所有属性
            String originName = field[j].getName(); //获取属性的名字

          String  name = originName.substring(0, 1).toUpperCase() + originName.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString(); //获取属性的类型
            //如果type是类类型，则前面包含"class "，后面跟类名
            Method m = model.getClass().getMethod("get" + name);
            Object value = m.invoke(model); //调用getter方法获取属性值

          String underLineStr=   MyStrUtils.camelToUnderline(originName,1);

        }

    }
}

