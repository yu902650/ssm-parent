package com.xiangxue.ch1;

/**
 * @author bo bo
 * @date 2019/6/11 0:37
 * @desc
 */
public class DaemonThread {

    private static class UseThread extends Thread{

        @Override
        public void run() {

            try {
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName() + " I am extend Thread");
                }
                System.out.println(Thread.currentThread().getName()
                +" interrupt flag is " + isInterrupted());
            } finally {
                System.out.println("........  finally  .........");
            }
        }
    }

    /**
     *      守护线程
     */
    public static void main(String[] args) throws InterruptedException {

        UseThread useThread = new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
//        useThread.interrupt();

    }
}
