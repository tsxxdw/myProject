package mycollection.mymap.myHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/18 14:20
 * @Description 测试HashMap的基本属性
 */
public class Main {
    public static void main(String[] args) {
         testKey();
        //testValue();
        //constructor();
        //hash("666");
        testResize();

    }


    public static void testKey() {
        Map<Integer, String> map = new HashMap();
        map.put(null, "a");
        map.put(null, "b");
        map.put(1, "1b");
        map.put(1, "2b");
        System.out.println(map.toString());
    }

    public static void testValue() {
        Map<Integer, String> map = new HashMap();
        map.put(1, "a");
        map.put(2, null);
        map.put(3, null);
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
        int res = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        System.out.println(res);
        return res;
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
