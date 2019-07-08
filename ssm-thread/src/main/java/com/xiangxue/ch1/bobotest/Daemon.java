package com.xiangxue.ch1.bobotest;

/**
 * @author bo bo
 * @date 2019/6/21 10:22
 * @desc
 */
public class Daemon {

    private static class UseThread extends Thread{

        @Override
        public void run() {

            try {
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " I am extend Thread");
                }
                System.out.println(Thread.currentThread().getName()+" interrupt flag is " + isInterrupted());
            } finally {
                System.out.println("--- finally ---");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        //开启守护线程
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);

    }


}
