package java8.demo.demo20190426;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        list.forEach(o -> {
            System.out.println(o);
        });
    }

    public static void test02() {
        myFunction(o1 -> String.valueOf(o1));//传入什么参数，就返回什么

        myFunction((Integer o1) -> String.valueOf(o1));//传入什么参数，就返回什么
        myFunction(o1 -> {
            // 业务代码
            return  String.valueOf(o1);
        });
        myFunction(o1 -> null);
        myFunction(o1 -> {
            return null;
        });
    }
    public static void myFunction(Function<Integer,String> function) {

    }
}
