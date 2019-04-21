package lock.demo1;

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
                synchronized (doctor) {
                    doctor.notifyAll();
                }
               /* while (true) {//如果这段代码没有被注释则线程1 中的cures方法总共只会执行一次
                    synchronized (doctor) {
                        doctor.notifyAll();

                        i++;
                        if (i < 100) {
                            System.out.printf("我是线程2的while循环");
                            doctor.notifyAll();
                        } else {
                            Thread.sleep(1000000);
                        }

                    }
                }*/
                // doctor.cures("1号");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
