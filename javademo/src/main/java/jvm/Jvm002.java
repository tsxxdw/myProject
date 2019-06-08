package jvm;

public class Jvm002 {
    String a1=new String("aaa");
    String a2=new String("aaa");
    public static void main(String[] args) {
        a1();
    }

    public static void a1(){

        Jvm002 jvm002=   new Jvm002();
        System.out.println(jvm002.a1==jvm002.a2);
    }
}
