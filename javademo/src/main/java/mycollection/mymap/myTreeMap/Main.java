package mycollection.mymap.myTreeMap;

import cn.tsxxdw.mybean.vo.ResultVo;

import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
          testKey();
        //  testValue();
        //testObjectAsKey();
        //testSort();
    }

    public static void testKey() {
        Map<String, String> map = new TreeMap<>();
        map.put("a1", "a1");
        map.put("a1", "a1");
        map.put("a2", "a2");
       map.put(null, "a3");
      //  map.put(null, "a4");
       // map.get("a2");
        Map<Integer, String> map1 = new TreeMap<>();
          map.put(null, "a4");

        System.out.println(map.toString());
        //结论：key 不可以为null ，否则报空指针错误
    }

    public static void testObjectAsKey() {
        Map<ResultVo, String> map = new TreeMap<>();
        ResultVo resultVo = new ResultVo<>();
        map.put(resultVo, "a1");

        System.out.println(map.toString());

    }

    public static void testValue() {
        Map<String, String> map = new TreeMap<>();
        map.put("a1", "a1");
        map.put("a2", "a2");
        map.put("a3", null);
        map.put("a4", null);
        System.out.println(map.toString());
        //结论：value 可以为null
    }


    public static void testSort() {
        Map<String, String> map = new TreeMap<>((o1, o2) -> o2.length() - o1.length());

        map.put("aaa1", "aaa1");
        map.put("aaa333", "aaa333");
        map.put("aaa55555", "aaa55555");
        map.put("aaa22", "aaa22");
        map.put("aaa888888888", "aaa888888888");
        System.out.println(map.toString());

    }
}
