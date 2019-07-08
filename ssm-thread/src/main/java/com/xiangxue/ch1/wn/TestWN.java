package com.xiangxue.ch1.wn;

/**
 * @author bo bo
 * @date 2019/6/19 10:22
 * @desc 测试 wait/notify/notifyAll
 */
public class TestWN {


    private static Express express = new Express(0, Express.CITY);

    /**
     * 检查里程数变化,不满足条件,线程一直等待
     */
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /**
     * check stie ,if not , wait along
     */
    private static class CheckSite extends Thread {

        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) { //三个线程
            new CheckSite().start();
        }

        for (int i = 0; i < 3; i++) { //里程数的变化
            new CheckKm().start();
        }

        Thread.sleep(1000);
        express.changeKm();  //快递地点编号
    }

}
