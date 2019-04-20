package myCollection.myMap.MyHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/4/20 11:04
 * @Description HashMap的基本实现
 */
public class MyHashMap {
    public static void main(String[] args) {
        constructor();
    }
    public static void constructor() {
        Map<Integer, String> originMap = new HashMap<>();
        originMap.put(1, "1");
        originMap.put(2, "2");
        Map<Integer, String> newMap = new HashMap<>(originMap);
        System.out.println(newMap.toString());
    }

}
