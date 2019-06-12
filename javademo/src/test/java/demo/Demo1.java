package demo;

import bean.Student;
import cn.tsxxdw.service.myDate.Mytimer;

import java.util.*;

public class Demo1 {
    public static void main(String[] args) {
         a1();

    }


    public static void a1(){
        int i=0;
        List<Student> list1=new ArrayList<>();
        List<Student> list2=new ArrayList<>();
        List<Student> list3=new ArrayList<>();
        while (i<100000){
            int n=(int)(Math.random()*10000);
            Student student=new Student();
            student.setName("小米");
            student.setYear(i);
            list1.add(student);

        }
        while (i<100000){
            int n=(int)(Math.random()*10000);
            Student student=new Student();
            student.setName("小米");
            student.setYear(i);
            list2.add(student);

        }
        Mytimer mytimer=  new Mytimer();
        mytimer.add("1");
        Map map1=new HashMap<>();
        list1.forEach(o->{
            map1.put(o.getYear(),o.getName());
        });
        list2.forEach(o->{
            if(map1.containsKey(o)){
                Student student=new Student();
                student.setYear(o.getYear());
                student.setName(o.getName());
                list3.add(student);
            }
        });
        mytimer.add("2");
        mytimer.getInfo();
    }
}
