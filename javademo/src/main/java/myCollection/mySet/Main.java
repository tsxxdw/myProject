package myCollection.mySet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author created by dsj
 * @Date 2019/4/20 9:30
 * @Description set的基本使用
 */
public class Main {
    public static void main(String[] args) {
        myHashSet();
    }


    public static void myHashSet() {
        MyHashSet.add();
        MyHashSet.repeat();
        MyHashSet.delete();
        MyHashSet.contains();
        System.out.println(
                "1 set 可以存储null值\n" +
                        "2 set 中存储的值是没有重复的\n" +
                        "3 set 的底层实现本质是一个HashMap"
        );
    }


}

