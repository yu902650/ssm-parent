package com.xiangxue.ch1.bobotest;

import java.util.concurrent.*;

/**
 * @author bo bo
 * @date 2019/6/21 9:50
 * @desc
 */
public class ImplRunnable {

    private static class UseRun implements Runnable{

        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }

    }

    public static void main(String[] args) {
        UseRun useRun = new UseRun();
        Thread thread = new Thread(useRun);
        thread.start();
        thread.interrupt();
    }
}
