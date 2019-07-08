package com.xiangxue.ch1.vola;

import com.xiangxue.tools.SleepTools;

/**
 * @author bo bo
 * @date 2019/6/11 20:47
 * @desc  volitile 无法提供操作的原子性
 */
public class VolatileUnsafe {

    private static  class VolatileVar implements Runnable{

        private volatile int a = 0;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            a = a+1;
            System.out.println(threadName+":======" +a);
            SleepTools.ms(100);
            a = a+1;
            System.out.println(threadName+":======" +a);
        }
    }

    public static void main(String[] args) {

        VolatileVar v  =new VolatileVar();

        Thread t1 = new Thread(v);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(v);
        Thread t4 = new Thread(v);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
