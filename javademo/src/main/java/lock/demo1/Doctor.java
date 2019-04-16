package lock.demo1;

public class Doctor {
    //治理的方法
    public synchronized void cures(String a){
            System.out.println("我正在治理:"+a);
        System.out.println(a+"治理结束");
    }
}
