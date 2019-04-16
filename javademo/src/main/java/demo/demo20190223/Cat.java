package demo.demo20190223;

/**
 * 这是一只猫
 */
public class Cat { //class 是关键字   Cat 是类名，且首字母大写
    private String color;//颜色，  基本变量 8大基本类型
    private String name;

    public void eat() {//基本方法
        System.out.println(name + "在吃鱼");
    }

    public Cat(String name) {//构造方法组成
        this.name = name;
    }

    public static void main(String[] args) {//这是一个入口，让程序知道从来里开始运行，就开始大门一样
        new Cat("小黑").eat();  //new 的方式可以创建对象
        new Cat("小黄").eat();
        Cat xiaoqing = new Cat("小青");//注意小青（变量名）只能用字母、数字（不能作为开头）和$
        Cat xiaoqing1  = new Cat("小青");
       /* 首字母为：字母、下划线、$;
        其余字母为：字母、下划线、$、数字;*/
        System.out.println(xiaoqing.getClass());
    }
    //客户端如何服务------对象.方法
}
