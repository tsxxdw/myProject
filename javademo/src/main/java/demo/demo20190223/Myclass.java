package demo.demo20190223;

/**
 * 1 类名首字母大写
 */
public class Myclass {
    public byte mybyte = 1; //Java中最小的数据类型，在内存中占8位(bit)，即1个字节，取值范围-128~127，默认值0

    protected short myShort; //：短整型，在内存中占16位，即2个字节，取值范围-32768~32717，默认值0

    int myInt;//：整型，用于存储整数，在内在中占32位，即4个字节，取值范围-2147483648~2147483647，默认值0

    private long mylong = 1;//长整型，在内存中占64位，即8个字节-2^63~2^63-1，默认值0L

    private float myfloat;//：浮点型，在内存中占32位，即4个字节，用于存储带小数点的数字（与double的区别在于float类型有效小数点只有6~7位），默认值0

    private double myDouble;//：双精度浮点型，用于存储带有小数点的数字，在内存中占64位，即8个字节，默认值0

    private char myChar;//字符型，用于存储单个字符，占16位，即2个字节，取值范围0~65535，默认值为空

    private boolean myboolean; //布尔类型，占1个字节，用于判断真或假（仅有两个值，即true、false），默认值false*/
/*
    public：

    具有最大的访问权限，可以访问任何一个在classpath下的类、接口、异常等。它往往用于对外的情况，也就是对象或类对外的一种接口的形式。

    protected：

    主要的作用就是用来保护子类的。它的含义在于子类可以用它修饰的成员，其他的不可以，它相当于传递给子类的一种继承的东西

    default：

    有时候也称为friendly，它是针对本包访问而设计的，任何处于本包下的类、接口、异常等，都可以相互访问，即使是父类没有用protected修饰的成员也可以。

    private：

    访问权限仅限于类的内部，是一种封装的体现，例如，大多数成员变量都是修饰符为private的，它们不希望被其他任何外部的类访问*/


    public void myMethod() {
        System.out.println("我是方法");
    }


    public Myclass() {
        System.out.println("我是构造方法");
    }

}
