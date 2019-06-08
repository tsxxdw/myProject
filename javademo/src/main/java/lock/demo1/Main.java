package lock.demo1;

/**
 * 结论： 1 当线程1 执行对象.wait方法之后，当前代码块不再执行，反之线程2开始执行，线程2 执行完毕之后，再接着执行线程1上次没有执行完的syso代码块
 */
public class Main {
    public static void main(String[] args) {

        Doctor doctor = new Doctor();
        thread1(doctor);
        thread2(doctor);
    }

    public static void thread1(Doctor doctor) {
        new Thread(() -> {
            System.out.println("我进入了第1个线程");
            while (true) {
                synchronized (doctor) {
                    try {
                        doctor.cures("1号");
                        doctor.wait();
                        System.out.println("1号线程执行完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }).start();
    }

    public static void thread2(Doctor doctor) {

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("我进入了第2个线程");
                int i = 0;
                while (true){
                    synchronized (doctor) {
                        doctor.cures("2号");
                        doctor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
