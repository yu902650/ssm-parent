package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @auther: bobo_yu
 * @create_time: 2018/12/21 22:19
 * @description: 无锁多线程
 * i = 20w
 */
public class DisappearRequest1 implements Runnable {

    static DisappearRequest1 instance = new DisappearRequest1();

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.err.println(i);
    }


    @Override
    public void run() {
        synchronized (DisappearRequest1.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }
}

/**
 * 1.方法上加synchronized
 * 2.代码块上加synchronized
 *  public void run() {
 *         synchronized (this) {
 *             for (int j = 0; j < 100000; j++) {
 *                 i++;
 *             }
 *         }
 *     }
 * 3. 类锁
 *    @Override
 *     public void run() {
 *         synchronized (DisappearRequest1.class) {
 *             for (int j = 0; j < 100000; j++) {
 *                 i++;
 *             }
 *         }
 *     }
 *
 */
