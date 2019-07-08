package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @author: bobo_yu
 * @create_time: 2018/12/22 13:21
 * @description:
 *      方法抛异常后，会释放锁。展示不抛出异常前和抛出异常后的对比：
 *          一旦抛出异常，第二个线程会立刻
 */
public class SynchronizedException implements Runnable {

    static SynchronizedException instance = new SynchronizedException();

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        } else {
            method2();
        }
    }

    public synchronized void method1() {
        System.out.println("我是静态加锁的方法1。 我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            throw new Exception();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName() + "运行结束");

    }

    public synchronized void method2() {
        System.out.println("我是非静态加锁的方法2。 我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("finished");
    }

}
