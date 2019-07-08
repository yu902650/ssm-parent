package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @auther: bobo_yu
 * @create_time: 2018/12/22 10:26
 * @description: 对象锁示例1 代码块形式
 *
 *
 *
 *      1. 默认this
 *      2. 自定义锁🔒
 */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();
    //创建锁对象--》 多个同步代码块
    Object lock1 = new Object();
    Object lock2 = new Object();
    @Override
    public void run() {
        //this 默认锁对象
        synchronized (lock1) {
            System.err.println("我是lock1的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "lock1运行结束🔚");
        }
        synchronized (lock2) {
            System.err.println("我是lock2的代码块形式。我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "lock2运行结束🔚");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {


        }
        System.err.println("finished");
    }

}
