package com.xiangxue.ch1;

import com.xiangxue.tools.SleepTools;

/**
 * @author bo bo
 * @date 2019/6/11 0:20
 * @desc
 */
public class StartAndRun {

    public static class ThreadRun extends Thread{

        @Override
        public void run() {
            int i = 90 ;
            while (i > 0){
                SleepTools.ms(1000);
                System.out.println("I AM " +Thread.currentThread().getName() + "and now the i=" + i--);
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun beCalled = new ThreadRun();
        beCalled.setName("BeCalled");

        beCalled.setPriority(10); //设置线程的优先级.操作系统不同,优先级设定不同,尽量不用
        beCalled.run(); // run 当成普通方法调用
        beCalled.start();  // start线程方法调用
    }
}
