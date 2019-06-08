package myCollection.myMap.myLinkedMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
/*
        testKey();
        testValue();
        testGet();
*/
        testNewTreeNode();
    }



    public static void testKey(){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("a1","a1");
        map.put("a1","a1");
        map.put("a2","a2");
        map.put(null,"a3");
        map.put(null,"a4");
        System.out.println(map.toString());
        //结论：key 可以为null ,value会被后面的替代
    }
    public static void testValue(){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("a1","a1");
        map.put("a2","a2");
        map.put("a3",null);
        map.put("a4",null);
        System.out.println(map.toString());
        //结论：key 可以为null
    }
    public static void testGet(){
        Map<String,String> map=new LinkedHashMap<>();
        map.put("a1","a1");
        map.put("a2","a2");
        map.put("a3","a3");
        System.out.println(map.toString());
        map.get("a2");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

    public  static void  testNewTreeNode(){
       Map <Integer,String> map= new LinkedHashMap<>();
       for(int i=0;i<10000;i++){
           map.put(i,String.valueOf(i));
       }
    }



}
