package myThread;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        //a1();
        //2();
        //a3();
        // a4();
        a5();



    }


    public static void a1() {
        new Thread(new MyRunnable1()).start();
    }

    public static void a2() {
        new MyThread2().start();
    }

    public static void a3() {//不建议，写起来麻烦
        MyCallable3 myCallable3 = new MyCallable3();
        FutureTask<String> futureTask = new FutureTask<>(myCallable3);
        new Thread(futureTask).start();
    }

    public static void a4() {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        int i = 0;
        for (; i < 50; i++) {
            int finalI = i;
            pool.submit(() -> System.out.println("我是任务" + finalI));
        }
        pool.shutdown();

    }

    public static void a5() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<String> res = pool.submit(() -> {
            Thread.sleep(5000);
            return "线程池实现Callable接口";
        });
        try {
            String re = null;
            if (res.isDone()) {
                re = res.get();
            }else {
                Thread.sleep(6000);
                re = res.get();
            }
            System.out.println(re);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //这里少了一句话，有谁知道？
    }


}


class MyRunnable1 implements Runnable {
    @Override
    public void run() {
        System.out.println("I is MyRunnable1");
    }
}


class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("I is MyThread2");
    }
}


class MyCallable3<V> implements Callable<V> {

    @Override
    public V call() throws Exception {
        System.out.println("I is MyCallable3");
        return null;
    }
}