package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @auther: bobo_yu
 * @create_time: 2018/12/22 10:26
 * @description: 对象锁示例1 代码块形式
 *
 *
 *      1. 默认this
 *      2. 自定义锁🔒
 *
 *      面试题：两个线程访问的是两个对象的同步方法
 */
public class SynchronizedObjectCodeBlock1 implements Runnable {

    static SynchronizedObjectCodeBlock1 instance1 = new SynchronizedObjectCodeBlock1();
    static SynchronizedObjectCodeBlock1 instance2 = new SynchronizedObjectCodeBlock1();

    @Override
    public void run() {
        //this 默认锁对象
        synchronized (this) {
            System.err.println("我是lock1的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "lock1运行结束🔚");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {


        }
        System.err.println("finished");
    }

}
