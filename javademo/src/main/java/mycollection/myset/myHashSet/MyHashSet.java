package mycollection.myset.myHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author created by dsj
 * @Date 2019/4/20 10:13
 * @Description HashSet
 */
public class MyHashSet {

    /**
     * set 中是能存储null元素的
     */
    public static void add() {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        set.add(null);
        System.out.println(set.toString());
    }

    /**
     * 添加重复的元素时，是不能添加的
     */
    public static void repeat() {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        boolean b1=set.add(1);
        boolean b2=set.add(1);
        boolean b1null=set.add(null);
        boolean b2null=set.add(null);
        System.out.println("在set中执行add方法添加重复元素的时候会返回false");
        System.out.println(set.toString());
    }


    /**
     * 删除的时候不能按照位置删除，因为他的位置会变化
     */
    public static void delete(){
        Set<String> set = new HashSet<>();
        set.add("0");
        set.remove(0);
        System.out.println(set.toString());
    }



    public static void contains(){
        Set<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);

        set.contains(null);
        if(set.contains(null)){
            System.out.println("set集合中包含元素null");
        }else {
            set.add(null);
            System.out.println("set集合中添加元素null之后就会包含元素null");
        }
    }

/**
 * 1 set 可以存储null值
 * 2 set 中存储的值是没有重复的
 * 3 set 的底层实现本质是一个HashMap
 */
}
