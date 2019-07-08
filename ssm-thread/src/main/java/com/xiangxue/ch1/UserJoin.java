package com.xiangxue.ch1;

import com.xiangxue.tools.SleepTools;

/**
 * @author bo bo
 * @date 2019/6/19 11:48
 * @desc  join method use
 */
public class UserJoin {

    static class JumpQueue implements Runnable{
        private Thread thread; //用来插队的线程

        public JumpQueue(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                System.out.println(thread.getName() + " will be jion before" + Thread.currentThread().getName());
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ "   terminate.");
        }
    }

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();//现在是主线程
        for (int i=0; i<10 ; i++){
            // i = 0 ;pervious 是主线程 I=1,

            Thread thread = new Thread(new JumpQueue(previous),String.valueOf(i));
            System.out.println(previous.getName()+" jump a queue the thread :"
            + thread.getName());
            thread.start();
            previous = thread;
        }

        SleepTools.second(2); //让主程序休眠2s.
        System.out.println(Thread.currentThread().getName()+"   terminate.");

    }


}
