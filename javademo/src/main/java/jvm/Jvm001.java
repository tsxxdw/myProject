package jvm;

public class Jvm001 {


    public static void main(String[] args) {
        //System.out.println(Child.p);  //调用静态变量的时候，静态变量实际上是哪个类所有就会初始化哪个类
        System.out.println(Child.c);  //初始化子类之前，会先初始化父类
    }
}

class Parent{
    public static String p="ParentStr";
    static {
        System.out.println("parent-static");
    }
}

class Child extends Parent{
    public static String c="childStr";
    static {
        System.out.println("child-static");
    }
}
