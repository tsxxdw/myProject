package cn.tsxxdw.service.myintutils;

public class MyIntUtils {
    public static int getRandom(int size){
        int random=(int)(Math.random()*size+1);
        System.out.println(random);
        return random;

    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            getRandom(100);
        }

    }


}
