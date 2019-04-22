package myCollection.myMap.MyHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/18 14:20
 * @Description 测试HashMap的基本属性
 */
public class Main {
    public static void main(String[] args) {
        // testKey();
     //  testValue();
     // /  constructor();
        hash("666");
    }


    public static void testKey() {
        Map<Integer, String> map = new HashMap();
        map.put(null, "a");
        map.put(null, "b");
        System.out.println(map.toString());
    }

    public static void testValue() {
        Map<Integer, String> map = new HashMap();
        map.put(1, "a");
        map.put(2, null);
        map.put(3, null);
        map.put(null, "b");
        System.out.println(map.toString());
    }

    public static void constructor() {
        Map<Integer, String> originMap = new HashMap<>();
        originMap.put(1, "1");
        originMap.put(2, "2");
        Map<Integer, String> newMap = new HashMap<>(originMap);
        System.out.println(newMap.toString());
    }



    static final int hash(Object key) {
        int h;
        int res= (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(res);
        return res;
    }

}


/*
 *  1 HashMap底层采用的是Entry数组和链表实现。
 *  2 实际上在Java中，HashSet的底层就是HashMap，并且HashSet仅仅使用HashMap的key作为集合中的元素。所以当我们对HashMap有深入的理解之后，HashSet也就是自然理解了。
 *
 */
