package com.xiangxue.ch1;

/**
 * @author bo bo
 * @date 2019/6/20 14:47
 * @desc 休眠的线程和不休眠的线程
 *
 *      sleep 的方法,不会主动释放锁.
 *      sync 中 尽量不使用sleep.
 */
public class SleepLock {

    private Object lock = new Object();

    private class ThreadSleep extends Thread {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock ");
            try {

                synchronized (lock) {
                    System.out.println(threadName + " taking the lock");
//                    Thread.sleep(5000);
                    System.out.println("Finish the work: " + threadName);
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " will take the lock time= "+System.currentTimeMillis());

                synchronized (lock) {
                    System.out.println(threadName + " taking the lock time= "+System.currentTimeMillis());
                    System.out.println("Finish the work: " + threadName);
                }

        }
    }

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();

        try {
            Thread.sleep(1000);
            System.out.println("Main slept ;");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

}
