package demo.demo20190223;

/**
 * 服务生的类
 */
public  class Waiter {
    private String name;//服务生姓名
    private int year;//年龄 -------int是范围的，

    public void provideMenu() {// 多峰定理，首字母小写，每个单词开头字母大写。
        //提供菜单的方法
        System.out.println("有小炒肉，蔬菜，鸡汤这三个菜");
    }

    private void getM() {//private代表着只有内部能够使用
        //计算收益
        System.out.println("今天赚了100快");
    }

    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        waiter.provideMenu();

        int intA = 2100000000;    //21亿
        System.out.println(intA);
        // int intB=2200000000;    //22亿
        // System.out.println(intB);
        //基本数据类型都是有范围限制的
        /*byte：Java中最小的数据类型，在内存中占8位(bit)，即1个字节，取值范围-128~127，默认值0

        short：短整型，在内存中占16位，即2个字节，取值范围-32768~32717，默认值0

        int：整型，用于存储整数，在内在中占32位，即4个字节，取值范围-2147483648~2147483647，默认值0

        long：长整型，在内存中占64位，即8个字节-2^63~2^63-1，默认值0L

        float：浮点型，在内存中占32位，即4个字节，用于存储带小数点的数字（与double的区别在于float类型有效小数点只有6~7位），默认值0

        double：双精度浮点型，用于存储带有小数点的数字，在内存中占64位，即8个字节，默认值0

        char：字符型，用于存储单个字符，占16位，即2个字节，取值范围0~65535，默认值为空

        boolean：布尔类型，占1个字节，用于判断真或假（仅有两个值，即true、false），默认值false*/
    }
}
