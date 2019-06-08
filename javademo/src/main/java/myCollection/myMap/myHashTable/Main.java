package myCollection.myMap.myHashTable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/18 14:20
 * @Description 测试HashMap的基本属性
 */
public class Main {
    public static void main(String[] args) {
         testKey();
     //   testValue();
        //constructor();
        //hash("666");
        //testResize();
        contains();

    }


    public static void testKey() {
        Map<Integer, String> map = new Hashtable<>();
        map.put(null, "a");
        map.put(null, "b");
        map.put(2, "1b");
        map.put(2, "2b");
        System.out.println(map.toString());
    }

    public static void testValue() {
        Map<Integer, String> map = new Hashtable();
        map.put(1, "a");
        map.put(2, null);
        map.put(3, null);
        map.put(3, null);
        System.out.println(map.toString());
    }

    public static void constructor() {
        Map<Integer, String> originMap = new Hashtable<>();
        originMap.put(1, "1");
        originMap.put(2, "2");
        Map<Integer, String> newMap = new Hashtable<>(originMap);
        System.out.println(newMap.toString());
    }


    static final int hash(Object key) {
        int h;
        int res = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(res);
        return res;
    }

    public static void contains(){
        Map<Integer, String> map = new Hashtable();
        map.put(1, "a");
        ((Hashtable<Integer, String>) map).contains(1);
        ((Hashtable<Integer, String>) map).contains("a");
        map.containsKey(1);
        map.containsValue("a");
    }





    /**
     * test 扩容
     */
    public static void testResize() {
        Map<Integer,String> map=new HashMap<>();
        map.put(0,String.valueOf(0));
        map.put(0,String.valueOf(0));
        map.put(1,String.valueOf(1));
        map.put(2,String.valueOf(2));
        map.put(10,String.valueOf(10));
        map.put(100,String.valueOf(100));
        map.put(1000,String.valueOf(1000));
        map.get(0);
        map.remove(0);
        System.out.println(map.toString());

    }

}


/*
 *  1 HashMap底层采用的是Entry数组和链表实现。
 *  2 实际上在Java中，HashSet的底层就是HashMap，并且HashSet仅仅使用HashMap的key作为集合中的元素。所以当我们对HashMap有深入的理解之后，HashSet也就是自然理解了。
 *
 */
