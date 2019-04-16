package lock.demo1;

public class Main {
    public static void main(String[] args) {
        Doctor doctor=    new Doctor();
       /* new Thread(()->{
            try {
                System.out.println("我进入了第1个线程");
                Thread.sleep(3000);
                System.out.println("我进入了第1个线程3秒");
                doctor.cures("1号");
                System.out.println("我离开了第1个线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
*/
        new Thread(()->{
            System.out.println("我进入了第2个线程");
            doctor.cures("2号");
            try {
                doctor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我离开了第2个线程");

        }).start();
    }
}
