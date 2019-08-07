package mycollection.myList.mylinkedlist;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list=new LinkedList<>();
        list.add(0);
        list.add(1);
        list.add(0);
        list.get(0);
        list.toString();
        list.remove(2);

    }
}
