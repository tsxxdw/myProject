package jvm.demo1;

public class Jvm002_1 {
    String a1=new String("aaa");
    String a2="aaa";
    public static void main(String[] args) {
        a1();
    }

    public static void a1(){

        Jvm002_1 jvm002=   new Jvm002_1();
        System.out.println(jvm002.a1==jvm002.a2);
    }
}
