package lock.demo3;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenshengsheng
 * @since 2019/6/8
 */
public class Lock {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    public static void main(String[] args) {
        new Thread(() -> {
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            method3();
        }).start();
    }

    public static void method1() throws InterruptedException {
        System.out.println("进入读锁1");
        readLock.lock();
        Thread.sleep(5000);
        System.out.println("method1处理完成...");
        readLock.unlock();
    }

    public static void method2() {
        System.out.println("进入读锁2");
        readLock.lock();
        System.out.println("method2处理完成...");
        readLock.unlock();
    }

    public static void method3() {
        System.out.println("进入写锁");
        writeLock.lock();
        System.out.println("method3...");
        writeLock.unlock();
    }
}
