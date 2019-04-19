package demo.demo20190223;


public class HelloWord {
    public static void main(String[] args) {
        String o="aaa-bbb";
        System.out.println(o.replace(o.substring(0,o.indexOf("-")+1), ""));;
        System.out.println(o.replace(o.substring(o.indexOf("-"),o.length()), ""));;
        System.out.println("hello world");
    }
}

// 1类名要大写，2要有入口main方法
