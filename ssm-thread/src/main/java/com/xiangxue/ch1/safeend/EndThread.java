package com.xiangxue.ch1.safeend;

/**
 * @author bo bo
 * @date 2019/6/6 16:03
 * @desc 如何安全的中断线程
 */
public class EndThread {

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) {
//            while (true) {   //无限跑下去.

                System.out.println(threadName + " is run ! ");
            }
//            System.out.println(threadName + "interrupt flage is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();
    }

}
